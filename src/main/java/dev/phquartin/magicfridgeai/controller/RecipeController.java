package dev.phquartin.magicfridgeai.controller;

import dev.phquartin.magicfridgeai.service.ChatGptService;
import dev.phquartin.magicfridgeai.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    private final FoodItemService foodItemService;
    private final ChatGptService chatGptService;
    public RecipeController(ChatGptService chatGptService, FoodItemService foodItemService) {
        this.chatGptService = chatGptService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> getRecipe() {
        return chatGptService.generateRecipe(foodItemService.findAll())
                .map(recipe -> ResponseEntity.ok().body(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
