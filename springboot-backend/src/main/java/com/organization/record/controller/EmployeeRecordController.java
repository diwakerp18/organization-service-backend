package com.organization.record.controller;

import com.organization.record.dto.EmployeeRecordDto;
import com.organization.record.entity.EmployeeRecord;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.service.EmployeeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("employee-record")
public class EmployeeRecordController {

    @Autowired
    private EmployeeRecordService employeeRecordService;

    // get all employee records
    @GetMapping("/get-all-employee-records")
    public List<EmployeeRecordDto> getAllEmployees() throws Exception {
        List<EmployeeRecordDto> employeeRecords = employeeRecordService.getEmployeeRecords();
        return employeeRecords;
    }

    // create employee record
    @PostMapping("/create-employee-record")
    public ResponseEntity<OrganizationServiceException> createEmployee(@Valid @RequestBody EmployeeRecordDto employeeRecordDto) throws Exception {
        EmployeeRecordDto retVal = employeeRecordService.createEmployeeRecord(employeeRecordDto);
        if (isNull(retVal)){
            throw new OrganizationServiceException("Employee Record Creation failed");
        }

        return ResponseEntity.ok(new OrganizationServiceException("Sucessfully Created Employee Record"));
    }

    // get employee by id
    @GetMapping("/get-employee-record/{id}")
    public ResponseEntity<EmployeeRecordDto> getEmployeeById(@PathVariable Long id) throws Exception {
        if (isNull(id) || id <= 0){
            throw new OrganizationServiceException("Student Id should be a positive value");
        }
        EmployeeRecordDto employeeRecordDto = employeeRecordService.getEmployeeById(id);
        return ResponseEntity.ok(employeeRecordDto);
    }

    // update employee record
    @PostMapping("/update-employee-record/{id}")
    public ResponseEntity<OrganizationServiceException> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRecordDto employeeRecordDto) throws  Exception {
        if (isNull(id) || id <= 0){
            throw new OrganizationServiceException("Employee Id should be a positive value");
        }
        employeeRecordDto.setId(id);

        EmployeeRecord retVal = employeeRecordService.updateEmployeeRecord(employeeRecordDto);
        if (isNull(retVal)){
            throw new OrganizationServiceException("Faild to update Employee record");
        }

        return ResponseEntity.ok(new OrganizationServiceException("Sucessfully Updated Employee Record"));
    }

    // Hard delete employee record
    @DeleteMapping("/hard-delete-employee-record/{id}")
    public ResponseEntity<OrganizationServiceException> hardDeleteEmployee(@PathVariable Long id) throws Exception {
        employeeRecordService.hardDeleteEmployee(id);
        return ResponseEntity.ok(new OrganizationServiceException("Sucessfully deleted employee with id :" + id));
    }

    // Soft Delete Employee record
    @PostMapping("/soft-delete-employee-record/{id}")
    public ResponseEntity<OrganizationServiceException> softDeleteEmployee(@PathVariable Long id) throws Exception {
        employeeRecordService.softDeleteEmployee(id);
        return ResponseEntity.ok(new OrganizationServiceException("Sucessfully deleted employee with id :" + id));
    }

    // Get Employee Records With Filter
    @GetMapping("/get-employees-by-filter")
    public List<EmployeeRecordDto> getEmployeesWithFilters(@QueryParam("branch") String branch, @QueryParam("role") String role) throws Exception {
        List<EmployeeRecordDto> employeeRecords = employeeRecordService.getEmployeesWithFilters(branch, role);
        return employeeRecords;
    }

}
