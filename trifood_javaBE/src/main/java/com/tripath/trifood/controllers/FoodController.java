package com.tripath.trifood.controllers;

import com.tripath.trifood.payloads.Dto.FoodDto;
import com.tripath.trifood.payloads.response.ApiResponse;
import com.tripath.trifood.payloads.response.FoodResponse;
import com.tripath.trifood.services.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("")
    public ResponseEntity<FoodDto> createFood(@RequestBody FoodDto foodDto){
        FoodDto createFood = this.foodService.createFood(foodDto);
        return new ResponseEntity<>(createFood, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<FoodResponse> getAllFoodes(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "foodId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        FoodResponse foodResponse = this.foodService.getAllFood(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(foodResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodDto> getFoodById(@PathVariable Integer foodId){
        FoodDto foodDto = this.foodService.getFoodById(foodId);
        return new ResponseEntity<>(foodDto, HttpStatus.OK);
    }

    @DeleteMapping("/{foodId}")
    public ApiResponse deleteFood(@PathVariable Integer foodId){
        this.foodService.deleteFood(foodId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<FoodDto> updateFood(@RequestBody FoodDto foodDto, @PathVariable Integer foodId){
        FoodDto updatedFood = this.foodService.updateFood(foodDto, foodId);
        return new ResponseEntity<>(updatedFood, HttpStatus.OK);
    }

    @GetMapping("/seach/{keyword}")
    public ResponseEntity<List<FoodDto>> searchFoodByName(@PathVariable("keyword") String keyword){
        List<FoodDto> result = this.foodService.searchFood(keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
