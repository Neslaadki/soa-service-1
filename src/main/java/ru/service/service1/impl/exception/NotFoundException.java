package ru.service.service1.impl.exception;

public class NotFoundException extends RuntimeException{

    public <T> NotFoundException(Class<T> entity, Long id){
        super(String.format("Not found %s with id %d", entity, id));
    }

}
