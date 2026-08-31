package com.socket.endpoint.service;

import com.socket.endpoint.messageresponse.SchemeResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SchemeProcessorTest {

    @Test
    void processSale() {

        SchemeProcessor schemeProcessor = new SchemeProcessor();

        SchemeResponse result = schemeProcessor.processSale();

        assertNotNull(result);
        assertEquals("00", result.getResponseCode());
        assertEquals("APPROVED", result.getResponseStatus());
    }

    @Test
    void processRefund() {

        SchemeProcessor schemeProcessor = new SchemeProcessor();

        SchemeResponse result = schemeProcessor.processRefund();

        assertNotNull(result);
        assertEquals("00", result.getResponseCode());
        assertEquals("APPROVED", result.getResponseStatus());
    }
}