package com.sushant.controller;


import com.sushant.dto.RestaurantDto;
import com.sushant.model.Restaurant;
import com.sushant.model.User;
import com.sushant.request.CreateRestaurantRequest;
import com.sushant.service.RestaurantService;
import com.sushant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurantList= restaurantService.searchRestaurant(keyword);
        return new ResponseEntity <>(restaurantList, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurantList= restaurantService.getAllRestaurant();
        return new ResponseEntity <>(restaurantList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
       Restaurant restaurant= restaurantService.findRestaurantById(id);
        return new ResponseEntity <>(restaurant, HttpStatus.OK);
    }


    @PutMapping("/{id}/add-favourites")
    public ResponseEntity<RestaurantDto> addToFavourites(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant= restaurantService.addToFavourites(id,user);
        return new ResponseEntity <>(restaurant, HttpStatus.OK);
    }




}
