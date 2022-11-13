package ru.service.service1.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<Response> handleDeleteException(DeleteException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }



}
