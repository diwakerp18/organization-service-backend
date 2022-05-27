package com.organization.record.service;

import com.organization.record.converter.StudentRecordDtoToEntityConverter;
import com.organization.record.converter.StudentRecordDtoToExistingEntityConverter;
import com.organization.record.converter.StudentRecordEntityToDtoConverter;
import com.organization.record.dto.StudentRecordDto;
import com.organization.record.entity.StudentRecord;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.helper.consts.StudentRecordConstants;
import com.organization.record.repository.StudentRecordRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class StudentRecordService {

    @Autowired
    StudentRecordRepo studentRecordRepo;

    @Autowired
    StudentRecordEntityToDtoConverter studentRecordEntityToDtoConverter;

    @Autowired
    StudentRecordDtoToEntityConverter studentRecordDtoToEntityConverter;

    @Autowired
    StudentRecordDtoToExistingEntityConverter studentRecordDtoToExistingEntityConverter;

    public List<StudentRecordDto> getStudentRecords() throws Exception {
        log.info("Fetching All Students Records");
        List<StudentRecord> studentRecords = studentRecordRepo.findAllByDeletedFalse();
        if (studentRecords.isEmpty()){
            throw new OrganizationServiceException("Not A Single Record Found");
        }

        List<StudentRecordDto> studentRecordDtos = studentRecordEntityToDtoConverter.entityToDto(studentRecords);
        return studentRecordDtos;
    }

    public StudentRecordDto createStudentRecord(StudentRecordDto studentRecordDto) throws Exception {
        log.info("creating new student record");
        if(isNull(studentRecordDto)){
            throw new OrganizationServiceException("data is empty");
        }

        StudentRecord existingStudentRecordForRollNumber = getStudentRecordForRollNumber(studentRecordDto.getRollNumber());
        if (!isNull(existingStudentRecordForRollNumber)) {
            throw new OrganizationServiceException("An StudentRecord Already Exists for rollNumber : "+ studentRecordDto.getRollNumber());
        }

        setDefaultValues(studentRecordDto);
        StudentRecord studentRecord = getEntityToSave(studentRecordDto);
        studentRecordRepo.save(studentRecord);
        StudentRecordDto studentRecordResponse = studentRecordEntityToDtoConverter.entityToDto(studentRecord);

        return studentRecordResponse;

    }

    public StudentRecordDto getStudentById(Long id) throws Exception {
        log.info("searching for student with id :"+ id);
        StudentRecord studentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(studentRecord)){
            throw new OrganizationServiceException("No Record Found With id :"+ id);
        }

        StudentRecordDto studentRecordDto = studentRecordEntityToDtoConverter.entityToDto(studentRecord);
        return studentRecordDto;
    }

    public StudentRecord updateStudentRecord(StudentRecordDto studentRecordDto) throws Exception {
        log.info("searching for student with id :"+ studentRecordDto.getId());
        StudentRecord existingStudentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(studentRecordDto.getId());
        if (isNull(existingStudentRecord)){
            throw new OrganizationServiceException("No Record Found For Id :"+ studentRecordDto.getId());
        }

        setDefaultValues(studentRecordDto);
        studentRecordDtoToExistingEntityConverter.dtoToExistingEntity(existingStudentRecord, studentRecordDto);
        return studentRecordRepo.save(existingStudentRecord);
    }

    public StudentRecord hardDeleteStudent(Long id) throws Exception {
        log.info("hard deleting student with id :"+ id);
        StudentRecord studentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(studentRecord)){
            throw new OrganizationServiceException("Student not exists with id :"+ id);
        }
        studentRecordRepo.delete(studentRecord);
        return studentRecord;
    }

    public StudentRecord softDeleteStudent(Long id) throws Exception {
        log.info("soft deleting student with id :"+ id);
        StudentRecord studentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(studentRecord)){
            throw new OrganizationServiceException("Student not exists with id :"+ id);
        }

        studentRecord.setDeleted(true);
        studentRecord.setUpdatedAt(new Date());
        studentRecordRepo.save(studentRecord);

        return studentRecord;
    }

    private StudentRecord getEntityToSave(StudentRecordDto studentRecordDto) {

        StudentRecord studentRecord = studentRecordDtoToEntityConverter.dtoToEntity(studentRecordDto);
        studentRecord.setCreatedAt(new Date());
        studentRecord.setUpdatedAt(new Date());
        return studentRecord;
    }

    public StudentRecord getStudentRecordForRollNumber(Integer rollNumber) throws Exception {

        if(isNull(rollNumber)){
            throw new OrganizationServiceException("rollNumber is null");
        }

        return studentRecordRepo.findFirstByRollNumberAndAndDeletedFalse(rollNumber);

    }

    private void setDefaultValues(StudentRecordDto studentRecordDto) {

        if (isNull(studentRecordDto.getDeleted())){
            studentRecordDto.setDeleted(StudentRecordConstants.DEFAULT_DELETED);
        }

    }

}
