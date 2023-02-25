package com.SashoEnterprises.TVCompany.Repositories;

import com.SashoEnterprises.TVCompany.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}
