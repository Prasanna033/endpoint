package com.socket.endpoint.service;

import com.socket.endpoint.messageresponse.SchemeResponse;

public class SchemeProcessor {

    public SchemeResponse processSale() {

        SchemeResponse response = new SchemeResponse();

        response.setResponseCode("00");
        response.setResponseStatus("APPROVED");

        return response;
    }

    public SchemeResponse processRefund() {

        return processSale();
    }
}