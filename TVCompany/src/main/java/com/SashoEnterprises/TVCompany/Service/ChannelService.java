package com.SashoEnterprises.TVCompany.Service;

import com.SashoEnterprises.TVCompany.Enums.Category;
import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Contract;
import com.SashoEnterprises.TVCompany.Models.Provider;
import com.SashoEnterprises.TVCompany.Repositories.ChannelRepository;
import com.SashoEnterprises.TVCompany.Repositories.ContractRepository;
import com.SashoEnterprises.TVCompany.Repositories.ProviderRepository;
import com.SashoEnterprises.TVCompany.Requests.CreateChannelRequest;
import com.SashoEnterprises.TVCompany.Requests.EditChannelRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepo;
    private final ContractService contractService;
    private final ProviderRepository providerRepository;

    public Channel loadChannelById(int id){
        return channelRepo.findChannelById(id);
    }
    public List<Channel> getChannels(){
        List<Channel> channels;
        channels = channelRepo.findAll();
        return channels;
    }
    public void createChannel(CreateChannelRequest request){
        Channel channel = new Channel();
        channel.setName(request.getName());
        channel.setCategory(request.getCategory());
        channel.setPrice(request.getPrice());
        Provider provider = providerRepository.findProviderById(Integer.parseInt(request.getProvider()));
        provider.addChannel(channel);
        channel.setProvider(provider);
        providerRepository.save(provider);
        channelRepo.save(channel);
    }
    public Channel loadChannelByName(String name){return channelRepo.findChannelByName(name);}
    public List<Channel> loadChannelsByProvider(Provider provider){return channelRepo.findChannelsByProvider(provider);}
    public void deleteChannel(String name){
        Channel channel = channelRepo.findChannelByName(name);
        List<Contract> contracts = contractService.getContractsByChannel(channel);
        Provider provider = providerRepository.findProviderByChannelSetContains(channel);
        if(provider!=null){
            provider.removeChannel(channel);
            channel.removeProvider();
        }
        if(!contracts.isEmpty()){
            for(int i=0;i< contracts.size();i++){
                double price = contracts.get(i).getPrice_per_month();
                price = price - channel.getPrice();
                price = Math.round(price*100.0)/100.0;
                contracts.get(i).setPrice_per_month(price);
                contracts.get(i).removeChannel(channel);
                contractService.save(contracts.get(i));
            }
            if(provider!=null){
                providerRepository.save(provider);
            }
            channelRepo.delete(channel);
        }
        else{
            if(provider!=null){
                providerRepository.save(provider);
            }
            channelRepo.delete(channel);
        }
    }
    public void editChannel(EditChannelRequest request)throws Exception{
        Channel channel = channelRepo.findChannelById(request.getId());
        if(!request.getName().isEmpty()){
            if(channelRepo.findChannelByName(request.getName())!=null){
                throw new Exception("Channel with that name already exists");
            }
            else{
                channel.setName(request.getName());
            }
        }
        if(request.getPrice()>=0){
            List<Contract> contracts = contractService.getContractsByChannel(channel);
            if(request.getPrice()==0){

            }
            else{
                if(!contracts.isEmpty()){
                    for(int i=0;i< contracts.size();i++){
                        double price = contracts.get(i).getPrice_per_month();
                        price = price - channel.getPrice() + request.getPrice();
                        price = Math.round(price*100.0)/100.0;
                        contracts.get(i).setPrice_per_month(price);
                        contractService.save(contracts.get(i));
                    }
                }
                channel.setPrice(request.getPrice());
            }
        }
        else {
            throw new Exception("price cannot be lower or equal to 0");
        }
        Provider provider = providerRepository.findProviderById(Integer.parseInt(request.getProvider()));
        provider.addChannel(channel);
        providerRepository.save(provider);
        channel.setProvider(provider);
        channelRepo.save(channel);
    }
    public List<Channel> loadChannelsByCategory(Category category){return channelRepo.findChannelsByCategory(category);}
}
