package com.SashoEnterprises.TVCompany.Service;

import com.SashoEnterprises.TVCompany.Models.Contract;
import com.SashoEnterprises.TVCompany.Models.User;
import com.SashoEnterprises.TVCompany.Repositories.ChannelRepository;
import com.SashoEnterprises.TVCompany.Repositories.ContractRepository;
import com.SashoEnterprises.TVCompany.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.SashoEnterprises.TVCompany.Models.Channel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepo;



    public Contract getContractById(int id){
        return contractRepo.findContractById(id);
    }
    public List<Contract> getContractsByChannel(Channel channel){return contractRepo.findContractsByChannelsContains(channel);}
    public List<Contract> getContracts(){
        return contractRepo.findAll();
    }
    public Contract createNewContract(List<Channel> channels,String length,User user){
        Contract contract = new Contract();
        double price = 0;
        for(int i=0;i<channels.size();i++){
            price = price + channels.get(i).getPrice();
            contract.addChannel(channels.get(i));
        }
        price=Math.round(price * 100.0) / 100.0;
        contract.setDate_from(LocalDate.now());
        contract.setDate_to(LocalDate.now().plusMonths(Integer.parseInt(length)));
        contract.setPrice_per_month(price);
        contract.setUser(user);
        user.addContract(contract);
        contractRepo.save(contract);
        return contract;
    }
    public void deleteContract(int id){
        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
        Contract contract = contractRepo.findContractById(id);
        if(contract.getUser()!=null){
            User user = contract.getUser();
            user.removeContract(contract);
            customUserDetailsService.saveUser(user);
        }
        contractRepo.delete(contract);
    }
    public void save(Contract contract){
        contractRepo.save(contract);
    }
}
