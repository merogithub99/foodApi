package com.sushant.service;

import com.sushant.model.IngredientCategory;
import com.sushant.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws  Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws  Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws  Exception;

    public  IngredientsItem createIngredientItem(Long restaurantId,String ingredientName,Long categoryId) throws  Exception;
    public  List<IngredientsItem> findRestaurantIngredients(Long restaurantId);

    public  IngredientsItem updateStock(Long id) throws Exception;
}
