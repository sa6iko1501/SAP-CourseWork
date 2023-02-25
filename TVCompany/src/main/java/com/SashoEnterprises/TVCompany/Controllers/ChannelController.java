package com.SashoEnterprises.TVCompany.Controllers;

import com.SashoEnterprises.TVCompany.Models.Channel;
import com.SashoEnterprises.TVCompany.Service.ChannelService;
import com.SashoEnterprises.TVCompany.Service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/channel")
@AllArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping("/lchannels")
    public String getChannels(Model model){
        model.addAttribute("channels",channelService.getChannels());
        return "channels";
    }

}
