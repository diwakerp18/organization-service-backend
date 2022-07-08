package com.organization.record.service;

import com.organization.record.converter.BatchEntityToDtoCoverter;
import com.organization.record.dto.BatchDto;
import com.organization.record.entity.Batch;
import com.organization.record.entity.Branch;
import com.organization.record.exception.OrganizationServiceException;
import com.organization.record.repository.BatchRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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

    public void verifyBatch(String batch) throws Exception {
        if (batch.isEmpty()){
            throw new OrganizationServiceException("batch number can`t be empty");
        }

        log.info("verifying the batch number : " + batch);
        Batch batchNo = batchRepo.findByBatchAndAndDeletedFalse(batch);
        if (isNull(batchNo)){
            throw new OrganizationServiceException("Batch Number You Have Entered Was Incorrect, We Only have These Listed Batches : 2017-2021, 2018-2022, 2019-2023, 2020-2024, 2021-2025, 2022-2026");
        }
    }
}
