package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
