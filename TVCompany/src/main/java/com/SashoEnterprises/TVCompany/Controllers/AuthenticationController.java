package com.SashoEnterprises.TVCompany.Controllers;

import com.SashoEnterprises.TVCompany.Models.User;
import com.SashoEnterprises.TVCompany.Requests.RegisterRequest;
import com.SashoEnterprises.TVCompany.Service.CustomUserDetailsService;
import com.SashoEnterprises.TVCompany.Service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
@AllArgsConstructor
public class AuthenticationController {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final ValidationService validationService;

    @GetMapping("/home")
    public String getHomepage(){return "home";}
    @GetMapping("/")
    public String getWelcomePG(){return "welcomePG";}
    @GetMapping("/register")
    public String getRegistration(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String getLogin(){return "login";}

    @GetMapping("/logout")
    public String logout(){
        return "welcomePG";
    }
    @PostMapping("/register")
    public String register(RegisterRequest request, Model model, HttpServletRequest sReq){
        User user;
        try{
            validationService.validateRegisterRequest(request);
            user = customUserDetailsService.registerUser(request);
        }
        catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
        authAfterRegistration(sReq, request.getUsername(), request.getPassword());
        System.out.println("Before redirect");
        return "redirect:/user/";
    }

    public void authAfterRegistration(HttpServletRequest request, String username, String password){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
