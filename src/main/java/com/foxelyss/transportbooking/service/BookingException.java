package com.foxelyss.transportbooking.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BookingException extends RuntimeException {
    public static enum ErrorType {SpaceAllocation, CantBeBooked, CantBeReturned}

    static final Map<ErrorType, String> ErrorName;

    static {
        Map<ErrorType, String> otherMap = new HashMap<>();
        otherMap.put(ErrorType.SpaceAllocation, "Все места заняты!");
        otherMap.put(ErrorType.CantBeBooked, "Рейс в прошлом или не найден!");
        otherMap.put(ErrorType.CantBeReturned, "Бронирование пропущено, возврат невозможен!");

        ErrorName = Collections.unmodifiableMap(otherMap);
    }

    ErrorType errorType;

    public BookingException(ErrorType error) {
        errorType = error;
    }

    @Override
    public String getMessage() {
        return ErrorName.get(errorType);
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
