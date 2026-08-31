package com.socket.endpoint.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EndpointMessageTransformTest {

    @Test
    void constructSaleRequest() throws Exception {

        EndpointMessageTransform transform = new EndpointMessageTransform();
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonRequest = "{}";

        assertNotNull(transform.constructSaleRequest(jsonRequest, objectMapper));
    }

    @Test
    void constructRefundRequest() throws Exception {

        EndpointMessageTransform transform = new EndpointMessageTransform();
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonRequest = "{}";

        assertNotNull(transform.constructRefundRequest(jsonRequest, objectMapper));
    }

    @Test
    void constructVerifyRequest() throws Exception {

        EndpointMessageTransform transform = new EndpointMessageTransform();
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonRequest = "{}";

        assertNotNull(transform.constructVerifyRequest(jsonRequest, objectMapper));
    }
}