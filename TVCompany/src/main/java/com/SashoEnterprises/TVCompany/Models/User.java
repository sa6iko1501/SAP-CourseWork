package com.SashoEnterprises.TVCompany.Models;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
@Id
    @GenericGenerator(name = "userGen",strategy = "increment")
    @GeneratedValue(generator = "userGen")
    private int id;
    private String first_name;
    private String family_name;
    private String phone;
    private String username;
    private String password;
    private String mail;
    @ManyToMany(fetch =  FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "user_contract",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "contract_id")
            }
    )
    private Set<Contract> contracts = new HashSet<>();
    public User(String username, String first_name, String family_name, String phone, String password,String mail){
        this.family_name=family_name;
        this.phone=phone;
        this.password=password;
        this.username=username;
        this.first_name=first_name;
        this.mail = mail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        Set<Role> roles;
        roles = getRoles();
        for(Iterator<Role> iterator = roles.iterator(); iterator.hasNext();){
            Role role = iterator.next();
            list.add(new SimpleGrantedAuthority((role.getName())));
        }
        return list;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void addContract(Contract contract){this.contracts.add(contract);}

    public void removeContract(Contract contract){this.contracts.remove(contract);}
}
