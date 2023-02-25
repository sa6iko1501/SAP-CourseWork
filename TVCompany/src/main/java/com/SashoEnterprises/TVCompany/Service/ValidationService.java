package com.SashoEnterprises.TVCompany.Service;

import com.SashoEnterprises.TVCompany.Enums.Category;
import com.SashoEnterprises.TVCompany.Requests.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidationService {
    private final ChannelService channelService;
    private final ProviderService providerService;
    public void validateDeleteChannelRequest(String name)throws Exception{
        if(channelService.loadChannelByName(name)==null){
            throw new Exception("Channel not found");
        }
    }
    public void validateEditChannelRequest(EditChannelRequest request) throws Exception{
        if(!request.getName().isEmpty()){
            if(!validateChannelName(request.getName())){
                throw new Exception("Name must have at least 2 characters");
            }
        }
        if(request.getPrice()<0){
            throw new Exception("Price cannot be lower or equal to 0");
        }
    }
    public void validateCreateProviderRequest(CreateProviderRequest request) throws Exception{
        if(!validateProviderName(request.getName())){
            throw new Exception("Invalid Name");
        }
        if(providerService.loadProviderByName(request.getName())!=null){
            throw new Exception("Name already taken!");
        }
    }
    public void validateRegisterRequest(RegisterRequest request) throws Exception{
        if(request.getUsername()==null||request.getPassword()==null||request.getPhone()==null||request.getFirst_name()==null
            ||request.getFamily_name()==null||request.getMail()==null){
            throw new Exception("Please fill up all the required fields!");
        }
        if(!(validatePhone(request.getPhone()))){
            throw new Exception("Please enter a valid number");
        }
        if(!(validateUsername(request.getUsername()))){
            throw new Exception("Username has to be between 5-20 characters");
        }
        if(!(validateEmail(request.getMail()))){
            throw new Exception("Please enter a valid e-mail address");
        }
        if(!(validatePassword(request.getPassword()))){
            throw new Exception(request.getPassword());
        }
    }

    public void validateCreateChannelRequest(CreateChannelRequest request) throws Exception{
        if(request.getPrice()<=0){
            throw new Exception("Price can't be lower or equal to 0");
        }
        if(request.getCategory()!=Category.music&&request.getCategory()!=Category.film&&request.getCategory()!=Category.sports
        &&request.getCategory()!=Category.documental&&request.getCategory()!=Category.news){
            throw new Exception("Invalid Category");
        }
        if(request.getName()==null){
            throw new Exception("Name cannot be empty");
        }
    }
    public void validateEditRequest(EditUserRequest request) throws Exception{
        if(!request.getUsername().isEmpty()){
            if(!(validateUsername(request.getUsername()))){
                throw new Exception("Username has to be between 5-20 characters");
            }
        }
        if(!request.getPhone().isEmpty()){
            if(!(validatePhone(request.getPhone()))){
                throw new Exception("Please enter a valid number");
            }
        }
        if(!request.getMail().isEmpty()){
            if(!(validateEmail(request.getMail()))){
                throw new Exception("Please enter a valid e-mail address");
            }
        }
        if(!request.getPassword().isEmpty()){
            if(!(validatePassword(request.getPassword()))){
                throw new Exception("Password must contain at least one digit, one lowercase character," +
                        "one uppercase character, one special character like @,!,$ and be at least 8 characters" +
                        "long");
            }
        }
    }
    private boolean validateEmail(String mail) {
        return mail.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }
    private boolean validateUsername(String username){
        return true;
    }
    private boolean validatePassword(String password){
        return true;
    }
    private boolean validatePhone(String phone){
        return phone.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
    }
    private boolean validateChannelName(String name){
        return true;
    }
    private boolean validateProviderName(String name){
        return true;
    }
}
