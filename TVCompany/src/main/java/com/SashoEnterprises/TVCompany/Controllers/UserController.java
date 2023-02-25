package com.SashoEnterprises.TVCompany.Controllers;

import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Models.Contract;
import com.SashoEnterprises.TVCompany.Models.User;
import com.SashoEnterprises.TVCompany.Requests.EditUserRequest;
import com.SashoEnterprises.TVCompany.Service.ChannelService;
import com.SashoEnterprises.TVCompany.Service.ContractService;
import com.SashoEnterprises.TVCompany.Service.CustomUserDetailsService;
import com.SashoEnterprises.TVCompany.Service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor

@RequestMapping("/user")
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;
    private final ValidationService validationService;
    private final ContractService contractService;
    private final ChannelService channelService;


    @GetMapping("/")
    public String getUserPage(Model model, Principal principal){
        model.addAttribute("user",customUserDetailsService.loadUserByMail(principal.getName()));
        return "user";
    }

    @GetMapping("/edit")
    public String editUserPage( Model model, Principal principal){
        model.addAttribute("user", customUserDetailsService.loadUserByMail(principal.getName()));
        return "userEdit";
    }

    @GetMapping("/adminPage")
    public String getAdminPage( Model model, Principal principal){
        UserDetails details = customUserDetailsService.loadUserByMail(principal.getName());
        model.addAttribute("user",details);
        if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "adminPage";
        }
        return "redirect:/home";

    }
    @GetMapping("/contracts")
    public String getContracts(Model model, Principal principal){
        User user = customUserDetailsService.loadUserByMail(principal.getName());
        model.addAttribute("contracts",user.getContracts());
        return "userContracts";
    }
    @GetMapping("/contracts/contract/{id}")
    public String getContractInfo(@PathVariable int id, Model model1, Model model2){
        Contract contract = contractService.getContractById(id);
        model1.addAttribute("contract",contract);
        model2.addAttribute("channels",contract.getChannels());
        return "contract";
    }

    @PostMapping("/edituser")
    public String editUser(Principal principal, EditUserRequest request, Model model){
        try{
            User user = customUserDetailsService.loadUserByMail(principal.getName());
            validationService.validateEditRequest(request);
            customUserDetailsService.editUser(user.getId(), request);
        }
        catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return  "home";
        }
        return "home";
    }

    @PostMapping("/finalizeContract")
    public String finalizeContract(Model model, @RequestParam(value = "channelIds",required = false) List<String> string_channels){
        List<Channel> channels= new ArrayList<>();
        if(string_channels == null){
            return "home";
        }
        double price = 0;
        for(int i= 0;i< string_channels.size();i++){
            channels.add(channelService.loadChannelById(Integer.parseInt(string_channels.get(i))));
            price = price + channels.get(i).getPrice();

        }
        price = Math.round(price * 100.0) / 100.0;
        model.addAttribute("channels",channels);
        model.addAttribute("total",price);
        return "finalizeContract";
    }
    @PostMapping("/createNewContract")
    public String createNewContract(@RequestParam("ids") List<Integer> ids, @RequestParam("length") String length, Principal principal, Model model){

        User user = customUserDetailsService.loadUserByMail(principal.getName());
        List<Channel> channels = new ArrayList<>();
        for(int i=0;i<ids.size();i++){
            channels.add(channelService.loadChannelById(ids.get(i)));
        }

        user.addContract(contractService.createNewContract(channels,length,user));
        customUserDetailsService.saveUser(user);
        getContracts(model, principal);
        return "home";
    }

    public void checkTime(){

    }
}
