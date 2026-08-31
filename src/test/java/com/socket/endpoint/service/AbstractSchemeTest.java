package com.socket.endpoint.service;

import com.socket.endpoint.messageresponse.SchemeResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractSchemeTest {

    @Test
    void processSale() {

        SchemeProcessor schemeProcessor = mock(SchemeProcessor.class);

        SchemeResponse response = new SchemeResponse();

        when(schemeProcessor.processSale()).thenReturn(response);

        AbstractScheme scheme = new AbstractScheme(schemeProcessor) {
        };

        assertEquals(response, scheme.processSale());
    }

    @Test
    void processRefund() {

        SchemeProcessor schemeProcessor = mock(SchemeProcessor.class);

        SchemeResponse response = new SchemeResponse();

        when(schemeProcessor.processRefund()).thenReturn(response);

        AbstractScheme scheme = new AbstractScheme(schemeProcessor) {
        };

        assertEquals(response, scheme.processRefund());
    }
}