package com.sushant.request;

import com.sushant.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
     private Address deliveryAddress;
}
