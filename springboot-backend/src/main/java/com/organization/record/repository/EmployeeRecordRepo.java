package com.organization.record.repository;

import com.organization.record.entity.EmployeeRecord;
import com.organization.record.entity.StudentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRecordRepo extends JpaRepository<EmployeeRecord, Long> {
    List<EmployeeRecord> findAllByDeletedFalse();
    EmployeeRecord findByIdAndAndDeletedFalse(Long id);
    List<EmployeeRecord> findByBranchAndRoleAndDeletedFalse(String branch, String role);
}
