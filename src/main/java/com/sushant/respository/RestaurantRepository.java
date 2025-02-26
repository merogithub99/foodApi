package com.sushant.respository;

import com.sushant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findByOwnerId(Long userId);

    @Query("SELECT  r from  Restaurant r where lower(r.name) LIKE lower(concat('%',:query,'%'))"+
            "OR lower(r.cuisineType) like  lower(concat('%',:query,'%') ) ")
    List<Restaurant> findBySearchQuery(String query);


}
