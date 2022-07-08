package com.organization.record.service;

import com.organization.record.converter.RoleEntityToDtoConverter;
import com.organization.record.dto.RoleDto;
import com.organization.record.entity.Role;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class RoleService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    RoleEntityToDtoConverter roleEntityToDtoConverter;

    public List<RoleDto> getRoles() throws Exception {
        log.info("Fetching All Roles");
        List<Role> roles= roleRepo.findAllByDeletedFalse();
        if (roles.isEmpty()){
            throw new OrganizationServiceException("Not A Single Record Found");
        }

        List<RoleDto> roleDtos = roleEntityToDtoConverter.entityToDto(roles);
        return roleDtos;
    }


    public void verifyRole(String role) throws Exception {
        if (role.isEmpty()){
            throw new OrganizationServiceException("role can`t be empty");
        }

        log.info("verifying the role : " + role);
        Role roleV2 = roleRepo.findByRoleAndDeletedFalse(role);
        if (isNull(roleV2)){
            throw new OrganizationServiceException("Role You Have Entered Was Incorrect, We Only have These Listed Roles : Admin, Dean, Hod, Assistant Professor, Network Admin, Accountant, Registrar, Student");
        }
    }
}
