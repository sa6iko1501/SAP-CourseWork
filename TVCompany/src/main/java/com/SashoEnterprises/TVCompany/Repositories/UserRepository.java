package com.SashoEnterprises.TVCompany.Repositories;

import com.SashoEnterprises.TVCompany.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
    User findUserByUsername(String username);
    User findUserByMail(String mail);
    User findUserByPhone(String phone);
    List<User> findAll();

}
