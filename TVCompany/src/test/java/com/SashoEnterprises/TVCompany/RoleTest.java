package com.SashoEnterprises.TVCompany;

import com.SashoEnterprises.TVCompany.Models.Role;
import com.SashoEnterprises.TVCompany.Repositories.RoleRepository;
import com.SashoEnterprises.TVCompany.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleTest {
    @Autowired
    RoleRepository roleRepo;
    @Test
    public void testCreateRoles(){
        Role user = new Role();
        Role admin = new Role();
        user.setName("USER");
        admin.setName("ADMIN");
        roleRepo.saveAll(List.of(user,admin));
        List<Role> roles = roleRepo.findAll();
        assert(!roles.isEmpty());
    }
}
