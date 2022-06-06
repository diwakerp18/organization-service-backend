package com.organization.record.converter;

import com.organization.record.dto.RoleDto;
import com.organization.record.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class RoleEntityToDtoConverter {
    public RoleDto entityToDto(Role role) {

        if(isNull(role)){
            return RoleDto.builder().build();
        }

        return RoleDto.builder()
                .id(role.getId())
                .role(role.getRole())
                .deleted(role.getDeleted())
                .build();
    }

    public List<RoleDto> entityToDto(List<Role> roleList) {
        return roleList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
