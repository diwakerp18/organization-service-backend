package com.organization.record.controller;

import com.organization.record.entity.EmployeeRecord;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.repository.EmployeeRecordRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employee-record")
public class EmployeeRecordController {
    @Autowired
    private EmployeeRecordRepo employeeRecordRepo;

    // get all employee records
    @GetMapping("/get-all-employee-records")
    public List<EmployeeRecord> getAllEmployees() throws Exception {
        log.info("Fetching All Employee Records");
        List<EmployeeRecord> employeeRecords = employeeRecordRepo.findAllByDeletedFalse();
        if (isNull(employeeRecords)){
            throw new OrganizationServiceException("Not A Single Record Found");
        }
        return employeeRecords;
    }

    // create employee record
    @PostMapping("/create-employee-record")
    public EmployeeRecord createEmployee(@RequestBody EmployeeRecord employeeRecord) throws Exception {
        log.info("creating new employee record");
        if (employeeRecord.getDeleted() == null){
        employeeRecord.setDeleted(false);
        }
        return employeeRecordRepo.save(employeeRecord);
    }

    // get employee by id
    @GetMapping("/get-employee-record/{id}")
    public ResponseEntity<EmployeeRecord> getEmployeeById(@PathVariable Long id) throws Exception {
        log.info("searching for employee with id :"+ id);
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(employeeRecord)){
            throw new OrganizationServiceException("No record found with id :"+ id);
        }
        return ResponseEntity.ok(employeeRecord);
    }

    // update employee record
    @PutMapping("/update-employee-record/{id}")
    public ResponseEntity<EmployeeRecord> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRecord employeeRecordDetails) throws Exception {
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(employeeRecord)){
            throw new OrganizationServiceException("No record found with id :"+ id);
        }
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
    public ResponseEntity<Map<String, Boolean>> hardDeleteEmployee(@PathVariable Long id) throws Exception {
        log.info("hard deleting employee with id :"+ id);
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(employeeRecord)){
            throw new OrganizationServiceException("No record found with id :"+ id);
        }
        employeeRecordRepo.delete(employeeRecord);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Soft Delete Employee record
    @PostMapping("/soft-delete-employee-record/{id}")
    public ResponseEntity<EmployeeRecord> softDeleteEmployee(@PathVariable Long id) throws Exception {
        log.info("soft deleting employee with id :"+ id);
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(employeeRecord)){
            throw new OrganizationServiceException("No record found with id :"+ id);
        }
        Boolean deleted = true;
        employeeRecord.setDeleted(deleted);
        employeeRecordRepo.save(employeeRecord);
        return ResponseEntity.ok(employeeRecord);
    }

}
