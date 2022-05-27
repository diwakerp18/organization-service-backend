package com.organization.record.converter;

import com.organization.record.dto.StudentRecordDto;
import com.organization.record.entity.StudentRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class StudentRecordEntityToDtoConverter {

    public StudentRecordDto entityToDto(StudentRecord studentRecord) {

        if(isNull(studentRecord)){
            return StudentRecordDto.builder().build();
        }

        return StudentRecordDto.builder()
                .id(studentRecord.getId())
                .collegeName(studentRecord.getCollegeName())
                .studentName(studentRecord.getStudentName())
                .emailId(studentRecord.getEmailId())
                .phoneNumber(studentRecord.getPhoneNumber())
                .rollNumber(studentRecord.getRollNumber())
                .deleted(studentRecord.getDeleted())
                .updatedAt(studentRecord.getUpdatedAt())
                .createdAt(studentRecord.getCreatedAt())
                .build();
    }

    public List<StudentRecordDto> entityToDto(List<StudentRecord> studentRecordList) {
        return studentRecordList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
