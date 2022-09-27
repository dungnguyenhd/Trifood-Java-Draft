package com.tripath.trifood.repositories;

import com.tripath.trifood.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
