package com.socket.endpoint.enums;

public enum ResponseCode {
    OK("00"),
    INVALID_CARD_NUMBER("01"),
    INVALID_CARD_EXPIRY("02"),
    INVALID_CARD_CVV("03"),
    TRANSACTION_NOT_FOUND("04");

    private final String statusCode;

    ResponseCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
