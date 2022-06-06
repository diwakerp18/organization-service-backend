package com.organization.record.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "Branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Branch")
    private String branch;
    @Column(name = "Deleted")
    private Boolean deleted;

    public Branch(){

    }

    public Branch(String branch, Boolean deleted){
        super();
        this.branch = branch;
        this.deleted = deleted;
    }

    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

}
