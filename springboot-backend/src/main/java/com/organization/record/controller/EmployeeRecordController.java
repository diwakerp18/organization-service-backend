package com.organization.record.controller;

import com.organization.record.entity.EmployeeRecord;
import com.organization.record.exception.ResourceNotFoundException;
import com.organization.record.repository.EmployeeRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employee-record")
public class EmployeeRecordController {
    @Autowired
    private EmployeeRecordRepo employeeRecordRepo;

    // get all employee records
    @GetMapping("/get-all-employee-records")
    public List<EmployeeRecord> getAllEmployees(){
        return employeeRecordRepo.findAllByDeletedFalse();
    }

    // create employee record
    @PostMapping("/create-employee-record")
    public EmployeeRecord createEmployee(@RequestBody EmployeeRecord employeeRecord) {
        if (employeeRecord.getDeleted() == null){
        employeeRecord.setDeleted(false);
        }
        return employeeRecordRepo.save(employeeRecord);
    }

    // get employee by id
    @GetMapping("/get-employee-record/{id}")
    public ResponseEntity<EmployeeRecord> getEmployeeById(@PathVariable Long id) {
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        return ResponseEntity.ok(employeeRecord);
    }

    // update employee record
    @PutMapping("/update-employee-record/{id}")
    public ResponseEntity<EmployeeRecord> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRecord employeeRecordDetails){
        EmployeeRecord employeeRecord = employeeRecordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        if (employeeRecord.getDeleted() == null){
            employeeRecord.setDeleted(false);
        }
        employeeRecord.setEmployeeName(employeeRecordDetails.getEmployeeName());
        employeeRecord.setCollegeName(employeeRecordDetails.getCollegeName());
        employeeRecord.setEmployeePosition(employeeRecordDetails.getEmployeePosition());
        employeeRecord.setPhoneNumber(employeeRecordDetails.getPhoneNumber());
        employeeRecord.setEmailId(employeeRecordDetails.getEmailId());

        EmployeeRecord updatedEmployeeRecord = employeeRecordRepo.save(employeeRecord);
        return ResponseEntity.ok(updatedEmployeeRecord);
    }

    // Hard delete employee record
    @DeleteMapping("/hard-delete-employee-record/{id}")
    public ResponseEntity<Map<String, Boolean>> hardDeleteEmployee(@PathVariable Long id){
        EmployeeRecord employeeRecord = employeeRecordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employeeRecordRepo.delete(employeeRecord);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Soft Delete Employee record
    @PostMapping("/soft-delete-employee-record/{id}")
    public ResponseEntity<EmployeeRecord> softDeleteEmployee(@PathVariable Long id) {

        EmployeeRecord employeeRecord = employeeRecordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        Boolean deleted = true;
        employeeRecord.setDeleted(deleted);
        employeeRecordRepo.save(employeeRecord);
        return ResponseEntity.ok(employeeRecord);
    }

}
