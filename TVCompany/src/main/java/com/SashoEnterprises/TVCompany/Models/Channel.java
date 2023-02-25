package com.SashoEnterprises.TVCompany.Models;

import com.SashoEnterprises.TVCompany.Enums.Category;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Channel {
    @Id
    @GenericGenerator(name = "chanGen",strategy = "increment")
    @GeneratedValue(generator = "chanGen")
    private int id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Provider provider;
    @Enumerated
    private Category category;
    private double price;
    public void removeProvider(){
        this.provider=null;
    }
}
