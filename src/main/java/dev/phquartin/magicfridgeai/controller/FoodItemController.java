package dev.phquartin.magicfridgeai.controller;

import dev.phquartin.magicfridgeai.model.FoodItem;
import dev.phquartin.magicfridgeai.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem saved = foodItemService.save(foodItem);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemService.findAll();
        return ResponseEntity.ok(foodItems);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
        FoodItem foodItem = foodItemService.findById(id);
        return ResponseEntity.ok(foodItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
