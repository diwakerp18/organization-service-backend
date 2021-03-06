package com.organization.record.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organization.record.entity.StudentRecord;

import java.util.List;

@Repository
public interface StudentRecordRepo extends JpaRepository<StudentRecord, Long>{
    List<StudentRecord> findAllByDeletedFalse();
    StudentRecord findFirstByRollNumberAndAndDeletedFalse(Integer rollNumber);
    StudentRecord findByIdAndAndDeletedFalse(Long id);
    List<StudentRecord> findByBranchAndBatchAndAndRoleAndDeletedFalse(String branch, String batch, String role);
}
