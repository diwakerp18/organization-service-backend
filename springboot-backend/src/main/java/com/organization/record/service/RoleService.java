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
}
