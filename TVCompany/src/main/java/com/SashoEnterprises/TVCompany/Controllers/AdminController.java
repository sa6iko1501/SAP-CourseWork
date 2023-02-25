package com.SashoEnterprises.TVCompany.Controllers;

import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Contract;
import com.SashoEnterprises.TVCompany.Models.Provider;
import com.SashoEnterprises.TVCompany.Models.User;
import com.SashoEnterprises.TVCompany.Requests.CreateChannelRequest;
import com.SashoEnterprises.TVCompany.Requests.CreateProviderRequest;
import com.SashoEnterprises.TVCompany.Requests.EditChannelRequest;
import com.SashoEnterprises.TVCompany.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final CustomUserDetailsService customUserDetailsService;
    private final ChannelService channelService;
    private final ProviderService providerService;
    private final ValidationService validationService;
    private final ContractService contractService;

    @GetMapping("/createChannel")
   public String channelCreationPage(Channel channel, Model model){
        model.addAttribute("providers",providerService.loadAll());
        return "channelCreation";
    }
    @PostMapping("/createChannel")
    public String createChannel(CreateChannelRequest request, Model model){
        try{
            validationService.validateCreateChannelRequest(request);
            channelService.createChannel(request);
        }
        catch(Exception e){
            model.addAttribute("error",e.getMessage());
        }
        return "adminPage";
    }
    @GetMapping("/showChannels")
    public String showChannelsAdmin(Model model){
        List<Channel> channels = channelService.getChannels();
        model.addAttribute("channels",channels);
        return "channelsAdmin";
    }


    @GetMapping("/deleteChannel/{id}")
    public String deleteChannel(@PathVariable int id, Model model){
        try{
            Channel channel = channelService.loadChannelById(id);
            if(channel==null){
                return "adminPage";
            }
            validationService.validateDeleteChannelRequest(channel.getName());
            channelService.deleteChannel(channel.getName());
        }
        catch(Exception e){
            model.addAttribute("error",e.getMessage());
        }
        return "adminPage";
    }
    @GetMapping("/editChannel/{id}")
    public String editChannelPage(@PathVariable int id,Channel channel1, Model model){
        Channel channel = channelService.loadChannelById(id);
        if(channel==null){
            return "adminPage";
        }
        List<Provider> providers = providerService.loadAll();
        model.addAttribute("unedited",channel);
        model.addAttribute("providers",providers);
        return "channelEdit";
    }
    @PostMapping("/editChannel")
    public String editChannel(EditChannelRequest editChannelRequest, Model model){
        try{
            validationService.validateEditChannelRequest(editChannelRequest);
            channelService.editChannel(editChannelRequest);
        }
        catch (Exception e){
            model.addAttribute("error",e.getMessage());
        }
        return "adminPage";
    }
    @GetMapping("/showUsers")
    public String userDeletionPage(Model model, Principal principal){
        User user = customUserDetailsService.loadUserByMail(principal.getName());
        List<User> users = customUserDetailsService.loadAllUsers();
        users.remove(user);
        model.addAttribute("users",users);
        return "userDeletion";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        if(customUserDetailsService.loadUserById(id)==null){
            return "adminPage";
        }
        customUserDetailsService.deleteUser(id);
        return "adminPage";
    }
    @GetMapping("/providers")
    public String showProviders(Provider provider, Model model){
        List<Provider> providers = providerService.loadAll();
        model.addAttribute("providers",providers);
        return "providers";
    }
    @GetMapping("/createProvider")
    public String createProviderPage(CreateProviderRequest request, Model model){
        model.addAttribute("request",request);
        return "createProvider";
    }
    @PostMapping("/createProvider")
    public String createProvider(CreateProviderRequest request, Model model){
        try{
            validationService.validateCreateProviderRequest(request);
            providerService.createProvider(request);
        }
        catch(Exception e){
            model.addAttribute("error",e.getMessage());
        }
        return "adminPage";
    }
    @GetMapping("/deleteProvider/{id}")
    public String deleteProvider(@PathVariable int id){
        if(providerService.loadProviderById(id)==null){
            return "adminPage";
        }
        providerService.deleteProvider(id);
        return "adminPage";
    }
    @GetMapping("/showUserInfo/{id}")
    public String showUserInfo(@PathVariable int id,Model model){
        User user = (User)customUserDetailsService.loadUserById(id);
        if(user==null){
            return "adminPage";
        }
        model.addAttribute("contracts",user.getContracts());
        return "userInfoAdmin";
    }
    @GetMapping("/deleteContract/{id}")
    public String deleteContract(@PathVariable int id){
        if(contractService.getContractById(id)==null){
            return "adminPage";
        }
        contractService.deleteContract(id);
        return "adminPage";
    }
    @GetMapping("/provider/{id}")
    public String showProviderInfo(@PathVariable int id, Model model){
        Provider provider = providerService.loadProviderById(id);
        if(provider == null){
            return "adminPage";
        }
        model.addAttribute("channels",provider.getChannelSet());
        return "providerInfo";
    }

}
