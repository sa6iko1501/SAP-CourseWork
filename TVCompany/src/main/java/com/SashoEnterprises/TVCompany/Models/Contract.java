package com.SashoEnterprises.TVCompany.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contract {
    @Id
    @GenericGenerator(name = "contractGen",strategy = "increment")
    @GeneratedValue(generator = "contractGen")
    private int id;
    private LocalDate date_from;
    private LocalDate date_to;
    private double price_per_month;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
        name = "contract_channel",
        joinColumns = {
                @JoinColumn(name = "contract_id")
        },
            inverseJoinColumns = {
                @JoinColumn(name = "channel_id")
            }
    )
    private Set<Channel> channels = new HashSet<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    public void addChannel(Channel channel){
        this.channels.add(channel);
    }
    public void removeChannel(Channel channel){
        this.channels.remove(channel);
    }
    public void removeAllChannels(){
        this.channels=null;
    }
    public void removeUser(){
        this.user=null;
    }
}
