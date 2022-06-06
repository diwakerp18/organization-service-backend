package com.organization.record.converter;

import com.organization.record.dto.BranchDto;
import com.organization.record.entity.Branch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class BranchEntityToDtoConverter {
    public BranchDto entityToDto(Branch branch) {

        if(isNull(branch)){
            return BranchDto.builder().build();
        }

        return BranchDto.builder()
                .id(branch.getId())
                .branch(branch.getBranch())
                .deleted(branch.getDeleted())
                .build();
    }

    public List<BranchDto> entityToDto(List<Branch> roleList) {
        return roleList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
