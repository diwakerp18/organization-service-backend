package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private Integer phoneNumber;
	
	public StudentRecord() {
		
	}
	
	public StudentRecord(String studentName, Integer rollNumber, String collegeName, String emailId, Integer phoneNumber) {
		super();
		this.studentName = studentName;
		this.rollNumber = rollNumber;
		this.collegeName = collegeName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getCollegeName() { return collegeName; }

	public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

	public String getEmailId() { return emailId; }

	public void setEmailId(String emailId) { this.emailId = emailId; }

	public Integer getPhoneNumber() { return phoneNumber; }

	public void setPhoneNumber(Integer phoneNumber) { this.phoneNumber = phoneNumber; }

	public Integer getRollNumber() { return rollNumber; }

	public void setRollNumber(Integer rollNumber) { this.rollNumber = rollNumber; }

	public String getStudentName() { return studentName; }

	public void setStudentName(String studentName) { this.studentName = studentName; }

}
