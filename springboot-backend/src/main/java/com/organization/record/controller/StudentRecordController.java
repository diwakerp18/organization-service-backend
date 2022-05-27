package com.organization.record.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.organization.record.entity.StudentRecord;
import com.organization.record.exception.OrganizationServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.organization.record.repository.StudentRecordRepo;

import static java.util.Objects.isNull;
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("student-record")
public class StudentRecordController {

	@Autowired
	private StudentRecordRepo studentRecordRepo;
	
	// get all student records
	@GetMapping("/get-all-student-records")
	public List<StudentRecord> getAllStudents(){
		log.info("Fetching All Students Records");
		List<StudentRecord> studentRecords = studentRecordRepo.findAllByDeletedFalse();
		if (studentRecords.isEmpty()){
			throw new OrganizationServiceException("Not A Single Record Found");
		}
		return studentRecords;
	}

	// create student record
	@PostMapping("/create-student-record")
	public StudentRecord createStudent(@RequestBody StudentRecord studentRecord) {
		log.info("creating new student record");
		if (isNull(studentRecord.getDeleted())){
		studentRecord.setDeleted(false);
		}
		return studentRecordRepo.save(studentRecord);
	}

	// get student by id
	@GetMapping("/get-student-record/{id}")
	public ResponseEntity<StudentRecord> getStudentById(@PathVariable Long id) {
		log.info("searching for student with id :"+ id);
		StudentRecord studentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(id);
		if (isNull(studentRecord)){
			throw new OrganizationServiceException("No Record Found With id :"+ id);
		}
		return ResponseEntity.ok(studentRecord);
	}

	// update student record
	@PutMapping("/update-student-record/{id}")
	public ResponseEntity<StudentRecord> updateStudent(@PathVariable Long id, @RequestBody StudentRecord studentRecordDetails){
		StudentRecord studentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(id);
		if (isNull(studentRecord)){
			throw new OrganizationServiceException("Student not exists with id :"+ id);
		}
		if (studentRecordDetails.getDeleted() == null){
			studentRecordDetails.setDeleted(false);
		}
		studentRecord.setStudentName(studentRecordDetails.getStudentName());
		studentRecord.setCollegeName(studentRecordDetails.getCollegeName());
		studentRecord.setRollNumber(studentRecordDetails.getRollNumber());
		studentRecord.setPhoneNumber(studentRecordDetails.getPhoneNumber());
		studentRecord.setEmailId(studentRecordDetails.getEmailId());

		StudentRecord updatedStudentRecord = studentRecordRepo.save(studentRecord);
		return ResponseEntity.ok(updatedStudentRecord);
	}

	// Hard delete student record
	@DeleteMapping("/hard-delete-student-record/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
		log.info("hard deleting student with id :"+ id);
		StudentRecord studentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(id);
		if (isNull(studentRecord)){
			throw new OrganizationServiceException("Student not exists with id :"+ id);
		}
		studentRecordRepo.delete(studentRecord);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	// Soft Delete student record
	@PostMapping("/soft-delete-student-record/{id}")
	public ResponseEntity<StudentRecord> softDeleteStudent(@PathVariable Long id) {
		log.info("soft deleting student with id :"+ id);
		StudentRecord studentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(id);
		if (isNull(studentRecord)){
			throw new OrganizationServiceException("Student not exists with id :"+ id);
		}
		Boolean deleted = true;
		studentRecord.setDeleted(deleted);
		studentRecordRepo.save(studentRecord);
		return ResponseEntity.ok(studentRecord);
	}

}
