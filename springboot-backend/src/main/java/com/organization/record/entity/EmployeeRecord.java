package com.organization.record.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "EmployeeRecord")
public class EmployeeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EmployeeName")
    private String employeeName;
    @Column(name = "EmployeePosition")
    private String employeePosition;
    @Column(name = "CollegeName")
    private String collegeName;
    @Column(name = "EmailId")
    private String emailId;
    @Column(name = "PhoneNumber")
    private Long phoneNumber;
    @Column(name = "Deleted")
    private Boolean deleted;
    @Column(name = "CreatedAt")
    private Date createdAt;
    @Column(name = "UpdatedAt")
    private Date updatedAt;

    public EmployeeRecord() {

    }

    public EmployeeRecord(String employeeName, String employeePosition, String collegeName, String emailId, Long phoneNumber, Date createdAt, Date updatedAt, Boolean deleted) {
        super();
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.collegeName = collegeName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getDeleted() { return deleted; }

    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
