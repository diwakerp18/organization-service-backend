package com.organization.record.converter;

import com.organization.record.dto.EmployeeRecordDto;
import com.organization.record.dto.StudentRecordDto;
import com.organization.record.entity.EmployeeRecord;
import com.organization.record.entity.StudentRecord;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class EmployeeRecordDtoToExistingEntityConverter {

    public EmployeeRecord dtoToExistingEntity(EmployeeRecord employeeRecord, EmployeeRecordDto employeeRecordDto) {

        employeeRecord.setEmployeeName(employeeRecordDto.getEmployeeName());
        employeeRecord.setCollegeName(employeeRecordDto.getCollegeName());
        employeeRecord.setEmployeePosition(employeeRecordDto.getEmployeePosition());
        employeeRecord.setEmailId(employeeRecordDto.getEmailId());
        employeeRecord.setPhoneNumber(employeeRecordDto.getPhoneNumber());
        employeeRecord.setUpdatedAt(new Timestamp(new Date().getTime()));

        return employeeRecord;
    }
}
