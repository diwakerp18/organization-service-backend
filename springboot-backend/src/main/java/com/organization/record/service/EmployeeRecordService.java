package com.organization.record.service;

import com.organization.record.converter.EmployeeRecordDtoToEntityConverter;
import com.organization.record.converter.EmployeeRecordDtoToExistingEntityConverter;
import com.organization.record.converter.EmployeeRecordEntityToDtoConverter;
import com.organization.record.dto.EmployeeRecordDto;
import com.organization.record.dto.StudentRecordDto;
import com.organization.record.entity.EmployeeRecord;
import com.organization.record.entity.StudentRecord;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.helper.consts.EmployeeRecordConstants;
import com.organization.record.repository.EmployeeRecordRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class EmployeeRecordService {
    @Autowired
    EmployeeRecordRepo employeeRecordRepo;
    @Autowired
    EmployeeRecordEntityToDtoConverter employeeRecordEntityToDtoConverter;
    @Autowired
    EmployeeRecordDtoToEntityConverter employeeRecordDtoToEntityConverter;
    @Autowired
    EmployeeRecordDtoToExistingEntityConverter employeeRecordDtoToExistingEntityConverter;
    @Autowired
    BranchService branchService;
    @Autowired
    RoleService roleService;

    public List<EmployeeRecordDto> getEmployeeRecords() throws Exception {
        log.info("Fetching All Employee Records");
        List<EmployeeRecord> employeeRecords = employeeRecordRepo.findAllByDeletedFalse();
        if (employeeRecords.isEmpty()){
            throw new OrganizationServiceException("Not A Single Record Found");
        }

        List<EmployeeRecordDto> employeeRecordDtos = employeeRecordEntityToDtoConverter.entityToDto(employeeRecords);
        return employeeRecordDtos;
    }

    public EmployeeRecordDto createEmployeeRecord(EmployeeRecordDto employeeRecordDto) throws Exception {
        log.info("creating new employee record");
        checkForNullInputs(employeeRecordDto);

        setDefaultValues(employeeRecordDto);
        EmployeeRecord employeeRecord = getEntityToSave(employeeRecordDto);
        employeeRecordRepo.save(employeeRecord);
        EmployeeRecordDto employeeRecordResponse = employeeRecordEntityToDtoConverter.entityToDto(employeeRecord);

        return employeeRecordResponse;
    }

    public EmployeeRecordDto getEmployeeById(Long id) throws Exception {
        log.info("searching for employee with id :"+ id);
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(employeeRecord)){
            throw new OrganizationServiceException("No Record Found With id :"+ id);
        }

        EmployeeRecordDto employeeRecordDto = employeeRecordEntityToDtoConverter.entityToDto(employeeRecord);
        return employeeRecordDto;
    }

    public EmployeeRecord updateEmployeeRecord(EmployeeRecordDto employeeRecordDto) throws Exception {
        log.info("searching for employee with id :"+ employeeRecordDto.getId());
        checkForEmptyInputs(employeeRecordDto);

        EmployeeRecord existingEmployeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(employeeRecordDto.getId());
        if (isNull(existingEmployeeRecord)){
            throw new OrganizationServiceException("No Record Found For Id :"+ employeeRecordDto.getId());
        }

        setDefaultValues(employeeRecordDto);
        employeeRecordDtoToExistingEntityConverter.dtoToExistingEntity(existingEmployeeRecord, employeeRecordDto);
        return employeeRecordRepo.save(existingEmployeeRecord);
    }

    public EmployeeRecord hardDeleteEmployee(Long id) throws Exception {
        log.info("hard deleting employee with id :"+ id);
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(employeeRecord)){
            throw new OrganizationServiceException("Student not exists with id :"+ id);
        }
        employeeRecordRepo.delete(employeeRecord);
        return employeeRecord;
    }

    public EmployeeRecord softDeleteEmployee(Long id) throws Exception {
        log.info("soft deleting employee with id :"+ id);
        EmployeeRecord employeeRecord = employeeRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(employeeRecord)){
            throw new OrganizationServiceException("No record found with id :"+ id);
        }

        employeeRecord.setDeleted(true);
        employeeRecord.setUpdatedAt(new Date());
        employeeRecordRepo.save(employeeRecord);

        return employeeRecord;
    }

    private EmployeeRecord getEntityToSave(EmployeeRecordDto employeeRecordDto) {

        EmployeeRecord employeeRecord = employeeRecordDtoToEntityConverter.dtoToEntity(employeeRecordDto);
        employeeRecord.setCreatedAt(new Date());
        employeeRecord.setUpdatedAt(new Date());
        return employeeRecord;
    }

    public List<EmployeeRecordDto> getEmployeesWithFilters(String branch, String role) throws Exception {
        getVerified(branch, role);
        log.info("Fetching All Employees Records With Branch {} And Role {} ", branch, role);
        List<EmployeeRecord> employeeRecords = employeeRecordRepo.findByBranchAndRoleAndDeletedFalse(branch, role);
        if (employeeRecords.isEmpty()){
            throw new OrganizationServiceException("Not A Single Record Found");
        }

        List<EmployeeRecordDto> employeeRecordDtos = employeeRecordEntityToDtoConverter.entityToDto(employeeRecords);
        return employeeRecordDtos;
    }

    private void getVerified(String branch, String role) throws Exception {
        if (isNull(branch)){
            throw new OrganizationServiceException("branch can`t` be empty");
        }
        branchService.verifyBranch(branch);

        if (isNull(role)){
            throw new OrganizationServiceException("role can`t` be empty");
        }
        roleService.verifyRole(role);
    }

    private void setDefaultValues(EmployeeRecordDto employeeRecordDto) {

        if (isNull(employeeRecordDto.getDeleted())){
            employeeRecordDto.setDeleted(EmployeeRecordConstants.DEFAULT_DELETED);
        }
    }

    private void checkForNullInputs(EmployeeRecordDto employeeRecordDto) {

        if(isNull(employeeRecordDto.getEmployeeName())){
            throw new OrganizationServiceException("Employee Name Can`t be empty");
        }
        if (isNull(employeeRecordDto.getCollegeName())){
            throw new OrganizationServiceException("College Name Can`t be empty");
        }
        if (isNull(employeeRecordDto.getEmailId())){
            throw new OrganizationServiceException("Email Id Can`t be empty");
        }
        if (isNull(employeeRecordDto.getPhoneNumber())){
            throw new OrganizationServiceException("Phone Number Can`t be empty");
        }
        if (isNull(employeeRecordDto.getRole())){
            throw new OrganizationServiceException("Role Can`t be empty");
        }
        if (isNull(employeeRecordDto.getBranch())){
            throw new OrganizationServiceException("Branch Can`t be empty");
        }
    }

    private void checkForEmptyInputs(EmployeeRecordDto employeeRecordDto) {

        if(employeeRecordDto.getEmployeeName().isEmpty()){
            throw new OrganizationServiceException("Employee Name Can`t be empty");
        }
        if (employeeRecordDto.getCollegeName().isEmpty()){
            throw new OrganizationServiceException("College Name Can`t be empty");
        }
        if (employeeRecordDto.getEmailId().isEmpty()){
            throw new OrganizationServiceException("Email Id Can`t be empty");
        }
        if (isNull(employeeRecordDto.getPhoneNumber())){
            throw new OrganizationServiceException("Phone Number Can`t be empty");
        }
        if (employeeRecordDto.getRole().isEmpty()){
            throw new OrganizationServiceException("Role Can`t be empty");
        }
        if (employeeRecordDto.getBranch().isEmpty()){
            throw new OrganizationServiceException("Branch Can`t be empty");
        }
    }

}
