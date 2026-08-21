package com.socket.endpoint.service;

import com.socket.endpoint.messageresponse.SchemeResponse;

public abstract class AbstractScheme {

    private final SchemeProcessor schemeProcessor;

    protected AbstractScheme(SchemeProcessor schemeProcessor) {
        this.schemeProcessor = schemeProcessor;
    }

    public SchemeResponse processSale() {
        return schemeProcessor.processSale();
    }

    public SchemeResponse processRefund() {
        return schemeProcessor.processRefund();
    }
}
