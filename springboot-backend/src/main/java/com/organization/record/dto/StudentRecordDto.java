package com.organization.record.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentRecordDto {

    private Long id;
    private String studentName;
    private Integer rollNumber;
    private String collegeName;
    private String emailId;
    private Long phoneNumber;
    private String role;
    private String branch;
    private  String batch;
    private Boolean deleted;
    private Date createdAt;
    private Date updatedAt;

}
