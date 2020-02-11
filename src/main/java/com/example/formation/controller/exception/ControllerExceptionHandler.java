package com.example.formation.controller.exception;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException methodArgumentNotValide) {
        Map<String, String> result = new HashMap<>();

        methodArgumentNotValide.getBindingResult().getAllErrors()
                .forEach(err -> {
                            String fieldName = ((FieldError) err).getField();
                            String errorMessage = err.getDefaultMessage();
                            result.put(fieldName, errorMessage);
                        }
                );
        return result;
    }


}
