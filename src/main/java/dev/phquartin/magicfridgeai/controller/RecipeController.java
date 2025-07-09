package dev.phquartin.magicfridgeai.controller;

import dev.phquartin.magicfridgeai.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    private final ChatGptService chatGptService;
    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> getRecipe() {
        return chatGptService.generateRecipe()
                .map(recipe -> ResponseEntity.ok().body(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
