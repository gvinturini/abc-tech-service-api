package br.com.fiap.abctechservice.handler.exception;

import lombok.Getter;

@Getter
public class AssistanceNotFoundException extends RuntimeException {
    private String description;

    public AssistanceNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }
}
