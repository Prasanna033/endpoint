package com.socket.endpoint.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MastercardSchemeImplTest {

    @Test
    void processSale() {
        SchemeProcessor schemeProcessor = new SchemeProcessor();
        MastercardSchemeImpl mastercardSchemeImpl = new MastercardSchemeImpl(schemeProcessor);
        assertEquals("MASTERCARD AUTHORISED", mastercardSchemeImpl.processSale().getResponseStatus());
    }
}