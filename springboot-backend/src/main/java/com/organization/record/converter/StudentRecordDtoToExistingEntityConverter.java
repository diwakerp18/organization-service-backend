package com.organization.record.converter;

import com.organization.record.dto.StudentRecordDto;
import com.organization.record.entity.StudentRecord;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class StudentRecordDtoToExistingEntityConverter {

    public StudentRecord dtoToExistingEntity(StudentRecord studentRecord, StudentRecordDto studentRecordDto) {

        studentRecord.setStudentName(studentRecordDto.getStudentName());
        studentRecord.setCollegeName(studentRecordDto.getCollegeName());
        studentRecord.setRollNumber(studentRecordDto.getRollNumber());
        studentRecord.setEmailId(studentRecordDto.getEmailId());
        studentRecord.setPhoneNumber(studentRecordDto.getPhoneNumber());
        studentRecord.setUpdatedAt(new Timestamp(new Date().getTime()));

        return studentRecord;
    }
}
