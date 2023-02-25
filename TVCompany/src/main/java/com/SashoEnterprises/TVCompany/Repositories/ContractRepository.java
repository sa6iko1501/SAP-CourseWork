package com.SashoEnterprises.TVCompany.Repositories;

import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ContractRepository extends JpaRepository<Contract,Integer> {
    Contract findContractById(int id);
    List<Contract> findContractsByChannelsContains(Channel channel);

}
