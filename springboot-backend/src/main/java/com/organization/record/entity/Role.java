package com.organization.record.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Role")
    private String role;
    @Column(name = "Deleted")
    private Boolean deleted;

    public Role(){

    }

    public Role(String role, Boolean deleted){
        super();
        this.role = role;
        this.deleted = deleted;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

}
