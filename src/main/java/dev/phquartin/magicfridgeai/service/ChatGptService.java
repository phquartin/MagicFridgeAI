package dev.phquartin.magicfridgeai.service;

import dev.phquartin.magicfridgeai.model.FoodItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final String apiKey = System.getenv("API_KEY");

    private final WebClient webClient;
    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItem> foodItems) {
        String prompt;

        if (foodItems == null || foodItems.isEmpty()) {
            prompt = "Estamos sem ingredientes para fazer receita! Voce deve avisar ao cliente que ele nao possui nada em estoque!";
        }else {
            String collect = foodItems.stream()
                    .map(item -> String.format("%s (%s) -- Quantidade: %d, Validade: %s",
                            item.getNome(), item.getCategoria().toString(), item.getQuantidade(), item.getValidade()))
                    .collect(Collectors.joining("\n"));
            prompt = "A lista de ingredientes para fazer receita é essa, caso não consiga fazer uma boa receita com isso avise o cliente, não esqueça de verificar a data de validade e a quantidade do produto \n Lista: \n" + collect;
        }

        String instructions = "Você é um chefe de cozinha e você vai sugerir uma receita para um cliente com base nos ingredientes que eu vou te passar, lembre-se de passar todas as instruções.";

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content", instructions
                        ),
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        var message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhuma receita gerada";
                }
                ).retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof WebClientResponseException.TooManyRequests)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure())
                );
    }

}
