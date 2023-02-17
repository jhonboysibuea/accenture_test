package com.accenture.asiggnment.controller;

import com.accenture.asiggnment.exception.DataNotFoundException;
import com.accenture.asiggnment.model.response.CustomErrorResponse;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@ControllerAdvice
public class BaseController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        List<String> mess=new LinkedList<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
            mess.add(MessageFormat.format(message,((FieldError) error).getRejectedValue().toString()));
        });

        return new ResponseEntity<Object>(CustomErrorResponse.buildNotValid(mess), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity handleDataNotFound(DataNotFoundException ex){
        CustomErrorResponse customErrorResponse;
        List<String> messages=new LinkedList<>();
        messages.add(ex.getMessage());
        customErrorResponse=CustomErrorResponse.buildNotUnique(messages);

        return new ResponseEntity(customErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleServerError(Exception e){
        return new ResponseEntity(CustomErrorResponse.systemError(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


