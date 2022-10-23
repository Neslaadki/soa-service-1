package ru.service.service1.impl.exception;

public class DeleteException extends RuntimeException{

    public <T> DeleteException(Class<T> entity, Long id){
        super(String.format("Can't delete object %s with id %d", entity, id));
    }

}
