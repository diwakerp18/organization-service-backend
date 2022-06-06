package com.organization.record.controller;

import com.organization.record.dto.BatchDto;
import com.organization.record.service.BatchService;
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
@RequestMapping("batch")
public class BatchController {

    @Autowired
    BatchService batchService;

    // get all batches
    @GetMapping("/get-all-batches")
    public List<BatchDto> getAllBatches() throws Exception {
        List<BatchDto> batchDtos = batchService.getBatches();
        return batchDtos;
    }
}
