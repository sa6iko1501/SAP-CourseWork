package com.SashoEnterprises.TVCompany.Requests;

import com.SashoEnterprises.TVCompany.Enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditChannelRequest {
    private int id;
    private String name;
    private Category category;
    private double price;

    private String provider;
}
