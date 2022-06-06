package com.organization.record.converter;

import com.organization.record.dto.EmployeeRecordDto;
import com.organization.record.entity.EmployeeRecord;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class EmployeeRecordDtoToExistingEntityConverter {

    public EmployeeRecord dtoToExistingEntity(EmployeeRecord employeeRecord, EmployeeRecordDto employeeRecordDto) {

        employeeRecord.setEmployeeName(employeeRecordDto.getEmployeeName());
        employeeRecord.setCollegeName(employeeRecordDto.getCollegeName());
        employeeRecord.setRole(employeeRecordDto.getRole());
        employeeRecord.setBranch(employeeRecordDto.getBranch());
        employeeRecord.setEmailId(employeeRecordDto.getEmailId());
        employeeRecord.setPhoneNumber(employeeRecordDto.getPhoneNumber());
        employeeRecord.setUpdatedAt(new Timestamp(new Date().getTime()));

        return employeeRecord;
    }
}
