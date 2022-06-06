package com.organization.record.controller;

import com.organization.record.dto.BranchDto;
import com.organization.record.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    // get all branches
    @GetMapping("/get-all-branches")
    public List<BranchDto> getAllBranches() throws Exception {
        List<BranchDto> branchDtos = branchService.getBranches();
        return branchDtos;
    }
}
