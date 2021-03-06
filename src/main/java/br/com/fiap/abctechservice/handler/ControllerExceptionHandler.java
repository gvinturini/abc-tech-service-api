package br.com.fiap.abctechservice.handler;

import br.com.fiap.abctechservice.handler.exception.AssistanceNotFoundException;
import br.com.fiap.abctechservice.handler.exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.exception.MinAssistsRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MinAssistsRequiredException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistsRequired(MinAssistsRequiredException ex) {
        ErrorMessageResponse error = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription()
        );
        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> validationErrorHandler(MethodArgumentNotValidException ex) {
        StringBuilder description = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            description.append(fieldError.getField() + " - " + fieldError.getDefaultMessage() + "; ");
        });
        ErrorMessageResponse error = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Invalid request",
                description.toString()
        );
        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxAssistsException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssistsException(MaxAssistsException ex) {
        ErrorMessageResponse error = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription()
        );
        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AssistanceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> errorAssistanceNotFoundException(AssistanceNotFoundException ex) {
        ErrorMessageResponse error = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription()
        );
        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.NOT_FOUND);
    }
}
