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

import static java.util.Objects.isNull;

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

    public void verifyBranch(String name) throws Exception {
        if (name.isEmpty()){
            throw new OrganizationServiceException("Name of the branch can`t be empty");
        }

        log.info("verifying the branch name : " + name);
        Branch branch = branchRepo.findByBranchAndAndDeletedFalse(name);
        if (isNull(branch)){
            throw new OrganizationServiceException("Branch Name You Have Entered Was Incorrect, We Only have These Listed Branches : ADMIN, CSE, MECH, ECE, CIVIL, PHARMA, NETWORK");
        }
    }
}
