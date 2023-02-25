package com.SashoEnterprises.TVCompany.Repositories;

import com.SashoEnterprises.TVCompany.Enums.Category;
import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {


    Channel findChannelById(int id);
    Channel findChannelByName(String name);
    List<Channel> findChannelsByCategory(Category category);
    List<Channel> findChannelsByProvider(Provider provider);
    List<Channel> findAll();
}
