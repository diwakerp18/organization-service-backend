package com.organization.record.converter;

import com.organization.record.dto.BatchDto;
import com.organization.record.entity.Batch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class BatchEntityToDtoCoverter {
    public BatchDto entityToDto(Batch batch) {

        if(isNull(batch)){
            return BatchDto.builder().build();
        }

        return BatchDto.builder()
                .id(batch.getId())
                .batch(batch.getBatch())
                .deleted(batch.getDeleted())
                .build();
    }

    public List<BatchDto> entityToDto(List<Batch> roleList) {
        return roleList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
