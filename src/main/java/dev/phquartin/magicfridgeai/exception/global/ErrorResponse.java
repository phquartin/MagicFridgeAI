package dev.phquartin.magicfridgeai.exception.global;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String mensagem;
    private int statusCode;
    private LocalDateTime timestamp;
    private String path;
}
