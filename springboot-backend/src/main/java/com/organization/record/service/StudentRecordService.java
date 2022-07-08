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
    @Autowired
    BranchService branchService;
    @Autowired
    BatchService batchService;
    @Autowired
    RoleService roleService;

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
        checkForNullInputs(studentRecordDto);

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
        checkForEmptyInputs(studentRecordDto);

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

        if (isNull(rollNumber) || rollNumber <= 0){
            throw new OrganizationServiceException("Student Name Can`t Be Empty or Negative or Zero");
        }

        log.info("Searching for student details with roll-number : " + rollNumber);
        StudentRecord studentRecord = studentRecordRepo.findFirstByRollNumberAndAndDeletedFalse(rollNumber);

        if (isNull(studentRecord)){
            throw new OrganizationServiceException("Student Record Not Found For Roll-Number : " + rollNumber);
        }

        return studentRecord;
    }

    public List<StudentRecordDto> getStudentsWithFilters(String branch, String batch, String role) throws Exception {
        getVerified(branch, batch, role);
        log.info("Fetching All Students Records With Branch {}, Batch {}, And Role {} ", branch, batch, role);
        List<StudentRecord> studentRecords = studentRecordRepo.findByBranchAndBatchAndAndRoleAndDeletedFalse(branch, batch, role);
        if (studentRecords.isEmpty()){
            throw new OrganizationServiceException("Not A Single Record Found");
        }

        List<StudentRecordDto> studentRecordDtos = studentRecordEntityToDtoConverter.entityToDto(studentRecords);
        return studentRecordDtos;
    }

    private void getVerified(String branch, String batch, String role) throws Exception {
        if (isNull(branch)){
            throw new OrganizationServiceException("branch can`t` be empty");
        }
        branchService.verifyBranch(branch);

        if (isNull(batch)){
            throw new OrganizationServiceException("batch can`t` be empty");
        }
        batchService.verifyBatch(batch);

        if (isNull(role)){
            throw new OrganizationServiceException("role can`t` be empty");
        }
        roleService.verifyRole(role);
    }

    private void setDefaultValues(StudentRecordDto studentRecordDto) {

        if (isNull(studentRecordDto.getDeleted())){
            studentRecordDto.setDeleted(StudentRecordConstants.DEFAULT_DELETED);
        }
        if (isNull(studentRecordDto.getRole())){
            studentRecordDto.setRole(StudentRecordConstants.DEFAULT_ROLE);
        }
    }

    private void checkForNullInputs(StudentRecordDto studentRecordDto) {

        if(isNull(studentRecordDto.getStudentName())){
            throw new OrganizationServiceException("Student Name Can`t be empty");
        }
        if (isNull(studentRecordDto.getCollegeName())){
            throw new OrganizationServiceException("College Name Can`t be empty");
        }
        if (isNull(studentRecordDto.getEmailId())){
            throw new OrganizationServiceException("Email Id Can`t be empty");
        }
        if (isNull(studentRecordDto.getPhoneNumber())){
            throw new OrganizationServiceException("Phone Number Can`t be empty");
        }
        if (isNull(studentRecordDto.getBatch())){
            throw new OrganizationServiceException("Batch Can`t be empty");
        }
        if (isNull(studentRecordDto.getBranch())){
            throw new OrganizationServiceException("Branch Can`t be empty");
        }
    }

    private void checkForEmptyInputs(StudentRecordDto studentRecordDto) {

        if(studentRecordDto.getStudentName().isEmpty()){
            throw new OrganizationServiceException("Student Name Can`t be empty");
        }
        if (studentRecordDto.getCollegeName().isEmpty()){
            throw new OrganizationServiceException("College Name Can`t be empty");
        }
        if (studentRecordDto.getEmailId().isEmpty()){
            throw new OrganizationServiceException("Email Id Can`t be empty");
        }
        if (isNull(studentRecordDto.getPhoneNumber())){
            throw new OrganizationServiceException("Phone Number Can`t be empty");
        }
        if (studentRecordDto.getBatch().isEmpty()){
            throw new OrganizationServiceException("Batch Can`t be empty");
        }
        if (studentRecordDto.getBranch().isEmpty()){
            throw new OrganizationServiceException("Branch Can`t be empty");
        }
        if (isNull(studentRecordDto.getRollNumber())){
            throw new OrganizationServiceException("Roll Number Can`t be empty");
        }
    }

}
