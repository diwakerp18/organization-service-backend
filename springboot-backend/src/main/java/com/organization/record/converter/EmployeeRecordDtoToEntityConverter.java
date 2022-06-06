package com.organization.record.converter;

import com.organization.record.dto.EmployeeRecordDto;
import com.organization.record.entity.EmployeeRecord;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRecordDtoToEntityConverter {

    public EmployeeRecord dtoToEntity(EmployeeRecordDto employeeRecordDto) {

        EmployeeRecord employeeRecord =  EmployeeRecord.builder()
                .id(employeeRecordDto.getId())
                .collegeName(employeeRecordDto.getCollegeName())
                .emailId(employeeRecordDto.getEmailId())
                .employeeName(employeeRecordDto.getEmployeeName())
                .phoneNumber(employeeRecordDto.getPhoneNumber())
                .role(employeeRecordDto.getRole())
                .branch(employeeRecordDto.getBranch())
                .deleted(employeeRecordDto.getDeleted())
                .build();

        return employeeRecord;
    }
}
