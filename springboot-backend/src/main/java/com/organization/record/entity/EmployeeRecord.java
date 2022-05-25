package com.organization.record.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public EmployeeRecord() {

    }

    public EmployeeRecord(String employeeName, String employeePosition, String collegeName, String emailId, Long phoneNumber) {
        super();
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.collegeName = collegeName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
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
}
