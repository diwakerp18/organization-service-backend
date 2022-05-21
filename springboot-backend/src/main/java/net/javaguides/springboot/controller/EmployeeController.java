package net.javaguides.springboot.controller;

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

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.StudentRecord;
import net.javaguides.springboot.repository.StudentRecordRepo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private StudentRecordRepo employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<StudentRecord> getAllEmployees(){
		return employeeRepository.findAll();
	}

	// create employee rest api
	@PostMapping("/employees")
	public StudentRecord createEmployee(@RequestBody StudentRecord studentRecord) {
		return employeeRepository.save(studentRecord);
	}

	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<StudentRecord> getEmployeeById(@PathVariable Long id) {
		StudentRecord studentRecord = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(studentRecord);
	}
	
	// update employee rest api
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<StudentRecord> updateEmployee(@PathVariable Long id, @RequestBody StudentRecord studentRecordDetails){
		StudentRecord studentRecord = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

		studentRecord.setStudentName(studentRecordDetails.getStudentName());
		studentRecord.setCollegeName(studentRecordDetails.getCollegeName());
		studentRecord.setRollNumber(studentRecordDetails.getRollNumber());
		studentRecord.setPhoneNumber(studentRecordDetails.getPhoneNumber());
		studentRecord.setEmailId(studentRecordDetails.getEmailId());

		StudentRecord updatedStudentRecord = employeeRepository.save(studentRecord);
		return ResponseEntity.ok(updatedStudentRecord);
	}

	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		StudentRecord studentRecord = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		employeeRepository.delete(studentRecord);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
