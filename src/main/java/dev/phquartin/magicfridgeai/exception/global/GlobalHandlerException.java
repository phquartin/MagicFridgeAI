package dev.phquartin.magicfridgeai.exception.global;

import dev.phquartin.magicfridgeai.exception.fooditem.FoodItemException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        ErrorResponse erro = ErrorResponse.builder()
                .mensagem(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(500)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FoodItemException.class)
    public ResponseEntity<ErrorResponse> handleFoodItemException(FoodItemException e, HttpServletRequest request) {
        ErrorResponse erro = ErrorResponse.builder()
                .mensagem(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(400)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
