package dev.phquartin.magicfridgeai.exception.fooditem;

import dev.phquartin.magicfridgeai.model.FoodItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

public class FoodItemValidation {

    public void validator(FoodItem foodItem) {
        LocalDate validade = foodItem.getValidade();
        Integer quantidade = foodItem.getQuantidade();

        if (LocalDate.now().isBefore(validade)) throw new FoodItemException("Validade precisa ser maior que a data atual");
        if (quantidade < 1) throw new FoodItemException("Quantidade precisa ser maior que 0");
    }

}
