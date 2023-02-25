package com.SashoEnterprises.TVCompany.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequest {
    private String username;
    private String phone;
    private String password;
    private String mail;
}
