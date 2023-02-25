package com.SashoEnterprises.TVCompany.Service;

import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Provider;
import com.SashoEnterprises.TVCompany.Repositories.ProviderRepository;
import com.SashoEnterprises.TVCompany.Requests.CreateProviderRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProviderService {
    private final ProviderRepository provRepo;
    private final ChannelService channelService;
    public Provider loadProviderById(int id){
        return provRepo.findProviderById(id);
    }
    public List<Provider> loadAll(){return provRepo.findAll();}
    public Provider loadProviderByName(String name){return  provRepo.findProviderByName(name);}
    public void createProvider(CreateProviderRequest request)throws Exception{
        if(provRepo.findProviderByName(request.getName())!=null){
            throw new Exception("Provider with that name already Exists");
        }
        Provider provider = new Provider();
        int i=0;
        try{
            i=Integer.parseInt(request.getMonths());
        }
        catch(IllegalArgumentException e){
            e.getMessage();
        }
        provider.setName(request.getName());
        provider.setDate_from(LocalDate.now());
        provider.setExp_date(LocalDate.now().plusMonths(i));
        provRepo.save(provider);
    }
    public void deleteProvider(int id){
        Provider provider = provRepo.findProviderById(id);
        if(!provider.getChannelSet().isEmpty()){
            List<Channel> channels= channelService.loadChannelsByProvider(provider);
            for(int i=0;i< channels.size();i++){
                channelService.deleteChannel(channels.get(i).getName());
            }
        }
        provRepo.delete(provider);
    }
    public void saveProvider(Provider provider){
        provRepo.save(provider);
    }
}
