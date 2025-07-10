package dev.phquartin.magicfridgeai.service;

import dev.phquartin.magicfridgeai.exception.fooditem.FoodItemException;
import dev.phquartin.magicfridgeai.exception.fooditem.FoodItemValidation;
import dev.phquartin.magicfridgeai.model.FoodItem;
import dev.phquartin.magicfridgeai.repository.FoodItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodItemValidation foodItemValidation;
    public FoodItemService(FoodItemRepository foodItemRepository, FoodItemValidation foodItemValidation) {
        this.foodItemRepository = foodItemRepository;
        this.foodItemValidation = foodItemValidation;
    }

    public FoodItem save(FoodItem foodItem) {

        // Validations
        if (foodItem.getNome() == null) throw new FoodItemException("Nome nulo");
        if (foodItem.getCategoria() == null) throw new FoodItemException("Categoria nula");
        if (foodItem.getQuantidade() == null) throw new FoodItemException("Quantidade nula");
        if (foodItem.getValidade() == null) throw new FoodItemException("Validade nula");
        foodItemValidation.validator(foodItem);

        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> findAll() {
        return foodItemRepository.findAll();
    }
    public FoodItem findById(Long id) {
        return foodItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID nao localizado no banco de dados"));
    }

    public void deleteById(Long id) {
        findById(id);
        foodItemRepository.deleteById(id);
    }

}
