package com.sushant.respository;

import com.sushant.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food>  findByRestaurantId(Long restaurantId);

    @Query("select f from Food f where f.name like %:keyword% OR  f.foodCategory.name like %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);
}
