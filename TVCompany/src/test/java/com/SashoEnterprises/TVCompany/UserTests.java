package com.SashoEnterprises.TVCompany;

import com.SashoEnterprises.TVCompany.Models.Role;
import com.SashoEnterprises.TVCompany.Models.User;
import com.SashoEnterprises.TVCompany.Repositories.RoleRepository;
import com.SashoEnterprises.TVCompany.Repositories.UserRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTests {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private EntityManager em;


    @Test
    public void testGrantedAuthorities(){
        User user = userRepo.findUserById(1);
        if (user != null && user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                System.out.println("This is a ADMIN");
        }
        assert user != null;
        System.out.println(user.getAuthorities().size());
        System.out.println(user.getRoles());
    }
}
