package com.sushant.controller;

import com.sushant.model.Food;
import com.sushant.model.Restaurant;
import com.sushant.model.User;
import com.sushant.request.CreateFoodRequest;
import com.sushant.response.MessageResponse;
import com.sushant.service.FoodService;
import com.sushant.service.RestaurantService;
import com.sushant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        foodService.deletedFood(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("food deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);


        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }


}
