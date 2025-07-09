package dev.phquartin.magicfridgeai.service;

import dev.phquartin.magicfridgeai.model.FoodItem;
import dev.phquartin.magicfridgeai.repository.FoodItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> findAll() {
        return foodItemRepository.findAll();
    }
    public FoodItem findById(Long id) {
        return foodItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Long id) {
        findById(id);
        foodItemRepository.deleteById(id);
    }

}
