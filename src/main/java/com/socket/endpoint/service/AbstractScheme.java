package com.socket.endpoint.service;

import com.socket.endpoint.messageRequests.RefundRequest;
import com.socket.endpoint.messageRequests.SalesRequest;
import com.socket.endpoint.messageResponse.SchemeResponse;

public abstract class AbstractScheme {

    private final SchemeProcessor schemeProcessor;

    public AbstractScheme(SchemeProcessor schemeProcessor) {
        this.schemeProcessor = schemeProcessor;
    }

    public SchemeResponse processSale(SalesRequest salesRequest) {
        return schemeProcessor.processSale(salesRequest);
    }

    public SchemeResponse processRefund(RefundRequest refundRequest) {
        return schemeProcessor.processRefund(refundRequest);
    }
}
