package com.organization.record.repository;

import com.organization.record.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findAllByDeletedFalse();
    Role findByRoleAndDeletedFalse(String role);
}
