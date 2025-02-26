package com.sushant.request;

import com.sushant.model.Address;
import com.sushant.model.ContactInformation;
import com.sushant.model.Restaurant;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private  Long id;
    private  String name;
    private String description;
    private  String cuisineType;
    private ContactInformation contactInformation;
    private  String openingHours;
    private List<String > images;
    private Address address;
}
