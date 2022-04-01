package br.com.fiap.abctechservice.handler.exception;

import lombok.Getter;

@Getter
public class MinAssistsRequiredException extends RuntimeException {
    private String description;

    public MinAssistsRequiredException(String message, String description) {
        super(message);
        this.description = description;
    }
}
