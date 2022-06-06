package com.organization.record.controller;

import com.organization.record.dto.RoleDto;
import com.organization.record.service.RoleService;
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
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    // get all roles
    @GetMapping("/get-all-roles")
    public List<RoleDto> getAllRoles() throws Exception {
        List<RoleDto> roleDtos = roleService.getRoles();
        return roleDtos;
    }

}
