package com.organization.record.service;

import com.organization.record.converter.BatchEntityToDtoCoverter;
import com.organization.record.dto.BatchDto;
import com.organization.record.entity.Batch;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.repository.BatchRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BatchService {
    @Autowired
    BatchRepo batchRepo;
    @Autowired
    BatchEntityToDtoCoverter batchEntityToDtoCoverter;

    public List<BatchDto> getBatches() throws Exception {
        log.info("Fetching All Batches");
        List<Batch> batches= batchRepo.findAllByDeletedFalse();
        if (batches.isEmpty()){
            throw new OrganizationServiceException("Not A Single Record Found");
        }

        List<BatchDto> batchDtos = batchEntityToDtoCoverter.entityToDto(batches);
        return batchDtos;
    }
}
