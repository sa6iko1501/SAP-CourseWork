package com.SashoEnterprises.TVCompany.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GenericGenerator(name = "roleGen",strategy = "increment")
    @GeneratedValue(generator = "roleGen")
    private int id;
    @Column(nullable = false)
    private String name;

    public Role(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
