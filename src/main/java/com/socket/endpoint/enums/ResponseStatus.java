package com.socket.endpoint.enums;


public enum ResponseStatus {
    AUTHORISED(ResponseCode.OK,"AUTHORISED"),
    INVALID_CARD_EXPIRY(ResponseCode.INVALID_CARD_EXPIRY,"Invalid Card Expiry"),
    INVALID_CARD_NUMBER(ResponseCode.INVALID_CARD_NUMBER,"Invalid Card Number"),
    INVALID_CARD_CVV(ResponseCode.INVALID_CARD_CVV,"Invalid Card CVV Number"),
    TRANSACTION_NOT_FOUND(ResponseCode.TRANSACTION_NOT_FOUND,"Transaction Not Found");

    private final ResponseCode responseCode;
    private final String statusMessage;
    ResponseStatus(ResponseCode responseCode, String statusMessage) {
        this.responseCode = responseCode;
        this.statusMessage = statusMessage;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
    public String getStatusMessage(){
        return statusMessage;
    }
}
