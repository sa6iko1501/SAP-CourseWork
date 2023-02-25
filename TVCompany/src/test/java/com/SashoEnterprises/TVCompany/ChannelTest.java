package com.SashoEnterprises.TVCompany;

import com.SashoEnterprises.TVCompany.Enums.Category;
import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Repositories.ChannelRepository;
import com.SashoEnterprises.TVCompany.Repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ChannelTest {
    @Autowired
    public EntityManager em;
    @Autowired
    public ChannelRepository channelRepository;
    @Autowired
    RoleRepository roleRepo;
    public void testChannelCreation(){
        Channel channel1 = new Channel();
        channel1.setName("City");
        channel1.setCategory(Category.music);
        channel1.setPrice(5.99);
        Channel channel2 = new Channel();
        channel2.setName("Ring TV");
        channel2.setCategory(Category.sports);
        channel2.setPrice(3.49);
        channelRepository.save(channel1);
        channelRepository.save(channel2);
    }


}
