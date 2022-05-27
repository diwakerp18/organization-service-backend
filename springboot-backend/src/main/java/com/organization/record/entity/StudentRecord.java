package com.organization.record.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

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
@Table(name = "StudentRecord")
public class StudentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "StudentName")
    private String studentName;
    @Column(name = "RollNumber")
    private Integer rollNumber;
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

    public StudentRecord() {

    }

    public StudentRecord(String studentName, Integer rollNumber, String collegeName, String emailId, Long phoneNumber, Date createdAt, Date updatedAt, Boolean deleted) {
        super();
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.collegeName = collegeName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCollegeName() { return collegeName; }

    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getEmailId() { return emailId; }

    public void setEmailId(String emailId) { this.emailId = emailId; }

    public Integer getRollNumber() { return rollNumber; }

    public void setRollNumber(Integer rollNumber) { this.rollNumber = rollNumber; }

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(Long phoneNumber) { this.phoneNumber = phoneNumber; }

    public Boolean getDeleted() { return deleted; }

    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

}
