package com.SashoEnterprises.TVCompany;

import com.SashoEnterprises.TVCompany.Enums.Category;
import com.SashoEnterprises.TVCompany.Models.*;
import com.SashoEnterprises.TVCompany.Repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestInitialization {
    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    ProviderRepository providerRepo;

    @Test
    public void initialization(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
         Role user = new Role();
         Role admin = new Role();
         user.setName("USER");
         admin.setName("ADMIN");
         roleRepo.saveAll(List.of(user,admin));
        User user1 = new User("admin1","Petar","Petrov","0899088831","admin123","admin1@abv.bg");
        user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword()));
        user1.addRole(roleRepo.findByName("ADMIN"));
        user1.addRole(roleRepo.findByName("USER"));
        User user2 = new User("user1","Ivan","Ivanov","0899088812","user123","user1@abv.bg");
        user2.addRole(roleRepo.findByName("USER"));
        user2.setPassword(bCryptPasswordEncoder.encode(user2.getPassword()));
        Provider provider1 = new Provider();
        Provider provider2 = new Provider();
        Provider provider3 = new Provider();
        provider1.setName("Media Group 1");
        provider2.setName("Media Group 2");
        provider3.setName("Media Group 3");
        provider1.setDate_from(LocalDate.now());
        provider2.setDate_from(LocalDate.now());
        provider3.setDate_from(LocalDate.now());
        provider1.setExp_date(LocalDate.now().plusMonths(6));
        provider2.setExp_date(LocalDate.now().plusMonths(12));
        provider3.setExp_date(LocalDate.now().plusMonths(24));
        List<Channel> channels = new ArrayList<>();
        for(int i=0;i<15;i++){
            Channel channel = new Channel();
            channel.setPrice(0.99+i);
            channel.setName("Channel "+(i+1));
            if(i<=2){
                channel.setCategory(Category.news);
                channel.setProvider(provider1);
                provider1.addChannel(channel);
            }
            if(2<i && i<=5){
                channel.setCategory(Category.film);
                channel.setProvider(provider1);
                provider1.addChannel(channel);
            }
            if(5<i && i<=8){
                channel.setCategory(Category.music);
                channel.setProvider(provider2);
                provider2.addChannel(channel);
            }
            if(8<i && i<=11){
                channel.setCategory(Category.documental);
                channel.setProvider(provider2);
                provider2.addChannel(channel);
            }
            if(11<i){
                channel.setCategory(Category.sports);
                channel.setProvider(provider3);
                provider3.addChannel(channel);
            }
            channels.add(channel);
        }
        Contract contract1 = new Contract();
        Contract contract2 = new Contract();
        Contract contract3 = new Contract();
        for(int i=0;i< channels.size();i++){
            contract1.addChannel(channels.get(i));
            contract1.setPrice_per_month(contract1.getPrice_per_month()+channels.get(i).getPrice());
            if(i%2==0){
                contract2.addChannel(channels.get(i));
                contract2.setPrice_per_month(contract2.getPrice_per_month()+channels.get(i).getPrice());
            }
            else{
                contract3.addChannel(channels.get(i));
                contract3.setPrice_per_month(contract3.getPrice_per_month()+channels.get(i).getPrice());
            }
        }
        contract1.setPrice_per_month(Math.round((contract1.getPrice_per_month())*100.0)/100.0);
        contract2.setPrice_per_month(Math.round((contract2.getPrice_per_month())*100.0)/100.0);
        contract3.setPrice_per_month(Math.round((contract3.getPrice_per_month())*100.0)/100.0);
        contract1.setDate_from(LocalDate.now());
        contract2.setDate_from(LocalDate.now());
        contract3.setDate_from(LocalDate.now());
        contract1.setDate_to(contract1.getDate_from().plusMonths(6));
        contract2.setDate_to(contract2.getDate_from().plusMonths(12));
        contract3.setDate_to(contract3.getDate_from().plusMonths(3));
        channelRepository.saveAll(channels);
        user2.addContract(contract1);
        user2.addContract(contract2);
        user1.addContract(contract3);
        contract1.setUser(user2);
        contract2.setUser(user2);
        contract3.setUser(user1);
        userRepo.save(user1);
        userRepo.save(user2);
        contractRepository.save(contract1);
        contractRepository.save(contract2);
        contractRepository.save(contract3);
        userRepo.save(user1);
        userRepo.save(user2);
        providerRepo.save(provider1);
        providerRepo.save(provider2);
        providerRepo.save(provider3);
    }
}
