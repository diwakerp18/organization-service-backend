package com.organization.record.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "Batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Batch")
    private String batch;
    @Column(name = "Deleted")
    private Boolean deleted;

    public Batch(){

    }

    public Batch(String batch, Boolean deleted){
        super();
        this.batch = batch;
        this.deleted = deleted;
    }

    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

}
