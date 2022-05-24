package com.organization.studentrecord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.organization.studentrecord.exception.ResourceNotFoundException;
import com.organization.studentrecord.entity.StudentRecord;
import com.organization.studentrecord.repository.StudentRecordRepo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("student-record")
public class StudentRecordController {

	@Autowired
	private StudentRecordRepo studentRecordRepo;
	
	// get all student records
	@GetMapping("/get-all-student-records")
	public List<StudentRecord> getAllStudents(){
		return studentRecordRepo.findAll();
	}

	// create student record
	@PostMapping("/create-student-record")
	public StudentRecord createStudent(@RequestBody StudentRecord studentRecord) {
		return studentRecordRepo.save(studentRecord);
	}

	// get student by id
	@GetMapping("/get-student-record/{id}")
	public ResponseEntity<StudentRecord> getStudentById(@PathVariable Long id) {
		StudentRecord studentRecord = studentRecordRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
		return ResponseEntity.ok(studentRecord);
	}

	// update student record
	@PutMapping("/update-student-record/{id}")
	public ResponseEntity<StudentRecord> updateStudent(@PathVariable Long id, @RequestBody StudentRecord studentRecordDetails){
		StudentRecord studentRecord = studentRecordRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

		studentRecord.setStudentName(studentRecordDetails.getStudentName());
		studentRecord.setCollegeName(studentRecordDetails.getCollegeName());
		studentRecord.setRollNumber(studentRecordDetails.getRollNumber());
		studentRecord.setPhoneNumber(studentRecordDetails.getPhoneNumber());
		studentRecord.setEmailId(studentRecordDetails.getEmailId());

		StudentRecord updatedStudentRecord = studentRecordRepo.save(studentRecord);
		return ResponseEntity.ok(updatedStudentRecord);
	}

	// delete student record
	@DeleteMapping("/delete-student-record/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
		StudentRecord studentRecord = studentRecordRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

		studentRecordRepo.delete(studentRecord);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
