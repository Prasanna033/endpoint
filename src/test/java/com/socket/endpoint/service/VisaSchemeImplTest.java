package com.socket.endpoint.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisaSchemeImplTest {

    @Test
    void processSale() {
        SchemeProcessor schemeProcessor = new SchemeProcessor();
        VisaSchemeImpl visaSchemeImpl=new VisaSchemeImpl(schemeProcessor);
        assertEquals("VISA AUTHORISED", visaSchemeImpl.processSale().getResponseStatus());
    }
}