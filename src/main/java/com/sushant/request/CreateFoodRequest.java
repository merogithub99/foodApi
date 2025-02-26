package com.sushant.request;

import com.sushant.model.Category;
import com.sushant.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private  String description;
    private Long price;

    private Category category;
    private List<String> images;

    private  Long restaurantId;
    private boolean vegeterian;
    private  boolean seasonal;

    private List<IngredientsItem> ingredients;

}
