package com.SashoEnterprises.TVCompany.Models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Provider {
@Id
@GenericGenerator(name = "provGen",strategy = "increment")
@GeneratedValue(generator = "provGen")
    private int id;
    private String name;
    private LocalDate date_from;
    private LocalDate exp_date;

    @OneToMany(mappedBy = "provider",fetch = FetchType.EAGER, cascade = CascadeType.REFRESH,orphanRemoval = true)
    private Set<Channel> channelSet = new HashSet<>();

    public void addChannel(Channel channel){
        this.channelSet.add(channel);
    }
    public void removeChannel(Channel channel){
        this.channelSet.remove(channel);
    }
    @Override
    public String toString(){
        return name;
    }

}
