package com.organization.record.converter;

import com.organization.record.dto.EmployeeRecordDto;
import com.organization.record.entity.EmployeeRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class EmployeeRecordEntityToDtoConverter {

    public EmployeeRecordDto entityToDto(EmployeeRecord employeeRecord) {

        if(isNull(employeeRecord)){
            return EmployeeRecordDto.builder().build();
        }

        return EmployeeRecordDto.builder()
                .id(employeeRecord.getId())
                .collegeName(employeeRecord.getCollegeName())
                .employeeName(employeeRecord.getEmployeeName())
                .emailId(employeeRecord.getEmailId())
                .phoneNumber(employeeRecord.getPhoneNumber())
                .role(employeeRecord.getRole())
                .branch(employeeRecord.getBranch())
                .deleted(employeeRecord.getDeleted())
                .updatedAt(employeeRecord.getUpdatedAt())
                .createdAt(employeeRecord.getCreatedAt())
                .build();
    }

    public List<EmployeeRecordDto> entityToDto(List<EmployeeRecord> employeeRecordList) {
        return employeeRecordList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
