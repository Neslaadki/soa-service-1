package ru.service.service1.api.exception;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    ORGANIZATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Организация не найдена"),
    ORGANIZATION_DELETED_EXCEPTION(HttpStatus.CONFLICT, "Произошла ошибка во время удаления");

    ErrorMessage(HttpStatus notFound, String s) {
    }
}
