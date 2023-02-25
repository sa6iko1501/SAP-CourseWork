package com.SashoEnterprises.TVCompany.Repositories;

import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    Provider findProviderById(int id);
    List<Provider> findAll();
    Provider findProviderByName(String name);

    Provider findProviderByChannelSetContains(Channel channel);
}
