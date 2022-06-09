package com.organization.record.controller;

import java.util.List;
import com.organization.record.dto.StudentRecordDto;
import com.organization.record.entity.StudentRecord;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.service.StudentRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.Objects.isNull;
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("student-record")
public class StudentRecordController {

	@Autowired
	private StudentRecordService studentRecordService;
	
	// get all student records
	@GetMapping("/get-all-student-records")
	public List<StudentRecordDto> getAllStudents() throws Exception {
		List<StudentRecordDto> studentRecords = studentRecordService.getStudentRecords();
		return studentRecords;
	}

	// create student record
	@PostMapping("/create-student-record")
	public ResponseEntity<OrganizationServiceException> createStudentRecord(@Valid @RequestBody StudentRecordDto studentRecordDto) throws  Exception {
		StudentRecordDto retVal = studentRecordService.createStudentRecord(studentRecordDto);
		if (isNull(retVal)){
			throw new OrganizationServiceException("Student Record Creation failed");
		}

		return ResponseEntity.ok(new OrganizationServiceException("Sucessfully Created Student Record"));
	}

	// get student by id
	@GetMapping("/get-student-record/{id}")
	public ResponseEntity<StudentRecordDto> getStudentById(@PathVariable Long id) throws Exception {
		if (isNull(id) || id <= 0){
			throw new OrganizationServiceException("Student Id should be a positive value");
		}
		StudentRecordDto studentRecordDto = studentRecordService.getStudentById(id);
		return ResponseEntity.ok(studentRecordDto);
	}

	// update student record
	@PostMapping("/update-student-record/{id}")
	public ResponseEntity<OrganizationServiceException> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRecordDto studentRecordDto) throws  Exception {
		if (isNull(id) || id <= 0){
			throw new OrganizationServiceException("Student Id should be a positive value");
		}
		studentRecordDto.setId(id);

		StudentRecord retVal = studentRecordService.updateStudentRecord(studentRecordDto);
		if (isNull(retVal)){
			throw new OrganizationServiceException("Faild to update student record");
		}
		return ResponseEntity.ok(new OrganizationServiceException("Sucessfully Updated Student Record"));
	}

	// Hard delete student record
	@DeleteMapping("/hard-delete-student-record/{id}")
	public ResponseEntity<OrganizationServiceException> hardDeleteStudent(@PathVariable Long id) throws Exception {
		StudentRecord studentRecord = studentRecordService.hardDeleteStudent(id);
		return ResponseEntity.ok(new OrganizationServiceException("Sucessfully deleted student with id :" + id));
	}

	// Soft Delete student record
	@PostMapping("/soft-delete-student-record/{id}")
	public ResponseEntity<OrganizationServiceException> softDeleteStudent(@PathVariable Long id) throws Exception {
		StudentRecord studentRecord = studentRecordService.softDeleteStudent(id);
		return ResponseEntity.ok(new OrganizationServiceException("Sucessfully deleted student with id :" + id));
	}

}
