package com.socket.endpoint.enums;

public enum ValidationError {
    INVALID_CARD_NUMBER("Invalid card number"),
    INVALID_CVV("Invalid cvv"),
    INVALID_AMOUNT("Invalid amount"),
    INVALID_CURRENCY("Invalid currency"),
    INVALID_EXPIRY_DATE("Invalid expiryDate"),
    INVALID_MERCHANT("Invalid merchant");

    ValidationError(String errorMessage){

    }
}
