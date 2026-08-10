package com.socket.endpoint.service;

import com.socket.endpoint.messageRequests.SalesRequest;
import com.socket.endpoint.messageRequests.RefundRequest;
import com.socket.endpoint.messageResponse.SchemeResponse;

public class SchemeProcessor {

    public SchemeResponse processSale(SalesRequest salesRequest) {

        SchemeResponse response = new SchemeResponse();

        response.setResponseCode("00");
        response.setResponseStatus("APPROVED");

        return response;
    }

    public SchemeResponse processRefund(RefundRequest refundRequest) {

        SchemeResponse response = new SchemeResponse();

        response.setResponseCode("00");
        response.setResponseStatus("APPROVED");

        return response;
    }
}