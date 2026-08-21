package com.socket.endpoint.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socket.endpoint.messagerequest.RefundRequest;
import com.socket.endpoint.messagerequest.SalesRequest;
import com.socket.endpoint.messagerequest.VerifyRequest;

public class EndpointMessageTransform {

    public SalesRequest constructSaleRequest(String jsonRequest, ObjectMapper objectMapper)
            throws JsonProcessingException {
        return objectMapper.readValue(jsonRequest, SalesRequest.class);
    }

    public RefundRequest constructRefundRequest(String jsonRequest, ObjectMapper objectMapper)
            throws JsonProcessingException {
        return objectMapper.readValue(jsonRequest, RefundRequest.class);
    }

    public VerifyRequest constructVerifyRequest(String jsonRequest, ObjectMapper objectMapper)
            throws JsonProcessingException {
        return objectMapper.readValue(jsonRequest,VerifyRequest.class);
    }
}
