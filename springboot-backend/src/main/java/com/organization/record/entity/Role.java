package com.organization.record.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Super;

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

    public Role(){

    }

    public Role(String role){
        super();
        this.role = role;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

}
