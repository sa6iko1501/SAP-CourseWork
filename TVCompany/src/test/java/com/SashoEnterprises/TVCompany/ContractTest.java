package com.SashoEnterprises.TVCompany;

import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Contract;
import com.SashoEnterprises.TVCompany.Models.User;
import com.SashoEnterprises.TVCompany.Repositories.ChannelRepository;
import com.SashoEnterprises.TVCompany.Repositories.ContractRepository;
import com.SashoEnterprises.TVCompany.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ContractTest {
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;
@Test
    public void contractTest(){
        User user = userRepository.findUserById(2);
        List<Channel> channels = channelRepository.findAll();
        Contract contract = new Contract();
        contract.setDate_from(LocalDate.now());
        contract.setDate_to(LocalDate.now());
        contract.setDate_to(contract.getDate_to().plusMonths(6));
        double price=0;
        for(int i=0;i<channels.size()-1;i++){
            contract.addChannel(channels.get(i));
            price = price+channels.get(i).getPrice();
        }
        contract.setUser(user);
        System.out.println(price);
        System.out.println(contract.getDate_from());
        System.out.println(contract.getDate_to());
        contract.setPrice_per_month(price);
        user.addContract(contract);
        contractRepository.save(contract);
        userRepository.save(user);
    }

    public void findbyChannel(){
        Channel channel = channelRepository.findChannelByName("CNN");
        List<Contract> contracts = contractRepository.findContractsByChannelsContains(channel);
        for(int i=0;i<contracts.size();i++){
            System.out.println(contracts.get(i).getUser().getMail());
        }
    }
}
