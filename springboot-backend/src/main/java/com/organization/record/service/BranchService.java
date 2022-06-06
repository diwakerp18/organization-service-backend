package com.organization.record.service;

import com.organization.record.converter.BranchEntityToDtoConverter;
import com.organization.record.dto.BranchDto;
import com.organization.record.entity.Branch;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.repository.BranchRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BranchService {
    @Autowired
    BranchRepo branchRepo;
    @Autowired
    BranchEntityToDtoConverter branchEntityToDtoConverter;

    public List<BranchDto> getBranches() throws Exception {
        log.info("Fetching All Branches");
        List<Branch> branches= branchRepo.findAllByDeletedFalse();
        if (branches.isEmpty()){
            throw new OrganizationServiceException("Not A Single Record Found");
        }

        List<BranchDto> branchDtos = branchEntityToDtoConverter.entityToDto(branches);
        return branchDtos;
    }
}
