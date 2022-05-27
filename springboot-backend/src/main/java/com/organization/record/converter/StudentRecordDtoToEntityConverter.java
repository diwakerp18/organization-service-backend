package com.organization.record.converter;

import com.organization.record.dto.StudentRecordDto;
import com.organization.record.entity.StudentRecord;
import org.springframework.stereotype.Service;

@Service
public class StudentRecordDtoToEntityConverter {

    public StudentRecord dtoToEntity(StudentRecordDto studentRecordDto) {


        StudentRecord studentRecord =  StudentRecord.builder()
                .id(studentRecordDto.getId())
                .collegeName(studentRecordDto.getCollegeName())
                .emailId(studentRecordDto.getEmailId())
                .studentName(studentRecordDto.getStudentName())
                .phoneNumber(studentRecordDto.getPhoneNumber())
                .rollNumber(studentRecordDto.getRollNumber())
                .deleted(studentRecordDto.getDeleted())
                .build();

        return studentRecord;
    }
}
