package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query(value ="SELECT * FROM food f WHERE f.food_name LIKE %?1%",nativeQuery = true)
    Page<Food> searchByName(String search, Pageable pageable);
}
