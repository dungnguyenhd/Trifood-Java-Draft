package com.tripath.trifood.repositories;

import com.tripath.trifood.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query(value ="SELECT * FROM food f WHERE f.food_name LIKE %?1%",nativeQuery = true)
    public List<Food> searchByName(String search);
}
