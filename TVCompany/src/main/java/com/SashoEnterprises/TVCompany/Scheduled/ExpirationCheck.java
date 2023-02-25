package com.SashoEnterprises.TVCompany.Scheduled;

import com.SashoEnterprises.TVCompany.Models.Provider;
import com.SashoEnterprises.TVCompany.Models.User;
import com.SashoEnterprises.TVCompany.Repositories.UserRepository;
import com.SashoEnterprises.TVCompany.Service.ContractService;
import com.SashoEnterprises.TVCompany.Service.ProviderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.SashoEnterprises.TVCompany.Models.Contract;

import java.time.LocalDate;
import java.util.List;

@Component
public class ExpirationCheck {
    public final ContractService contractService;
    public final ProviderService providerService;
    public final UserRepository userRepository;
    public ExpirationCheck(ContractService contractService, ProviderService providerService, UserRepository userRepository) {
        this.contractService = contractService;
        this.providerService = providerService;
        this.userRepository = userRepository;
    }


    @Scheduled(fixedRate = 86400000)
    public void expirationCheck(){
        LocalDate currentDate = LocalDate.now();
        int magicId = 0;
        System.out.println("The date is: "+currentDate);
        List<Contract> contracts = contractService.getContracts();
        List<Provider> providers = providerService.loadAll();
        try{
            for(int i=0;i< contracts.size();i++){
                if(contracts.get(i).getDate_to().isEqual(currentDate)||contracts.get(i).getDate_to().isBefore(currentDate)){
                    int id = contracts.get(i).getId();
                    User user = contracts.get(i).getUser();
                    user.removeContract(contracts.get(i));
                    userRepository.save(user);
                    contracts.remove(contracts.get(i));
                    contractService.deleteContract(id);
                }
            }
            for(int i=0;i< providers.size();i++){
                if(providers.get(i).getExp_date().isEqual(currentDate)||providers.get(i).getExp_date().isBefore(currentDate)){
                    magicId = providers.get(i).getId();
                    providerService.deleteProvider(providers.get(i).getId());
                }
            }
            if(contracts.isEmpty()&& providers.isEmpty()){
                System.out.println("Everything is up to date!");
            }
        }
        catch(Exception e){
            providerService.deleteProvider(magicId);
            System.out.println(e.getMessage());
        }
    }
}
