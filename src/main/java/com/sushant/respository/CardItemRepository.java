package com.sushant.respository;

import com.sushant.model.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardItemRepository extends JpaRepository<CardItem,Long> {
}
