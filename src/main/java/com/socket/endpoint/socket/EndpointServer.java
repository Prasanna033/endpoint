package com.socket.endpoint.socket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socket.endpoint.messagerequest.RefundRequest;
import com.socket.endpoint.messagerequest.SalesRequest;
import com.socket.endpoint.messagerequest.VerifyRequest;
import com.socket.endpoint.messageresponse.SalesPostResponse;
import com.socket.endpoint.service.EndpointMessageTransform;
import com.socket.endpoint.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EndpointServer {

    private final MessageService messageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointServer.class.getName());

    private volatile boolean running = true;

    public EndpointServer(MessageService messageService) {
        this.messageService = messageService;
    }

    public void startServer() {

        try (ServerSocket serverSocket = new ServerSocket(5000)){

            LOGGER.info("Endpoint Server Started...");

            while (running) {
                LOGGER.info("Waiting for Gateway...");

                Socket socket = serverSocket.accept();

                LOGGER.info("Gateway Connected");

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));

                String jsonRequest = reader.readLine();

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode jsonNode= objectMapper.readTree(jsonRequest);
                String transactionType=jsonNode.get("transactionType").asText();

                LOGGER.info("Transaction Type : {}",transactionType);

                EndpointMessageTransform endpointMessageTransform=new EndpointMessageTransform();


                SalesPostResponse response=new SalesPostResponse();
                if("SALE".equalsIgnoreCase(transactionType)) {

                    SalesRequest salesRequest = endpointMessageTransform.constructSaleRequest(jsonRequest, objectMapper);
                    response = messageService.processRequest(salesRequest);
                } else if ("REFUND".equalsIgnoreCase(transactionType)) {
                    RefundRequest refundRequest= endpointMessageTransform.constructRefundRequest(jsonRequest,objectMapper);
                    response= messageService.processRefundRequest(refundRequest);
                } else if ("VERIFY".equalsIgnoreCase(transactionType)) {
                    VerifyRequest verifyRequest = endpointMessageTransform.constructVerifyRequest(jsonRequest, objectMapper);
                    response = messageService.processVerifyRequest(verifyRequest);
                }

                LOGGER.info("response object : {}",response);

                String jsonResponse =
                        objectMapper.writeValueAsString(response);

                LOGGER.info("json response : {}",jsonResponse);

                PrintWriter writer =
                        new PrintWriter(socket.getOutputStream(), true);

                writer.println(jsonResponse);
        }

            } catch(Exception e){
                LOGGER.error(e.getMessage());
            }
        }
    }




