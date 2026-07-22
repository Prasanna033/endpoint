package com.socket.endpoint.service;

import com.socket.endpoint.model.SalesPostResponse;
import com.socket.endpoint.model.SalesRequest;

public class MessageService {

    public SalesPostResponse processRequest(SalesRequest request) {

        SalesPostResponse response = new SalesPostResponse();

        response.setTransactionId("TXN100001");
        response.setResponseCode("00");
        response.setResponseMessage("Transaction Successful");

        return response;
    }
}
