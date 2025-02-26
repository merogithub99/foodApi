package com.sushant.service;

import com.sushant.model.Category;
import com.sushant.model.Food;
import com.sushant.model.Restaurant;
import com.sushant.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deletedFood(Long foodId) throws  Exception;

    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegeterian,
                                        boolean isNonVeg,
                                        boolean isSeasonal,
                                        String foodCategory);

    public  List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;

    public  Food updateAvailabilityStatus(Long foodId) throws Exception;



}
