package com.socket.endpoint.socket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socket.endpoint.messageRequests.RefundRequest;
import com.socket.endpoint.messageRequests.VerifyRequest;
import com.socket.endpoint.messageResponse.SalesPostResponse;
import com.socket.endpoint.messageRequests.SalesRequest;
import com.socket.endpoint.service.EndpointMessageTransform;
import com.socket.endpoint.service.MessageService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EndpointServer {

    private MessageService messageService;

    public EndpointServer(MessageService messageService) {
        this.messageService = messageService;
    }

    public void startServer() {

        try {

            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Endpoint Server Started...");

            while (true) {
                System.out.println("Waiting for Gateway...");

                Socket socket = serverSocket.accept();

                System.out.println("Gateway Connected");

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));

                String jsonRequest = reader.readLine();

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode jsonNode= objectMapper.readTree(jsonRequest);
                String transactionType=jsonNode.get("transactionType").asText();

                System.out.println("Transaction Type :" +transactionType);

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

                System.out.println("response object : "+response);

                String jsonResponse =
                        objectMapper.writeValueAsString(response);

                System.out.println("json response :"+jsonResponse);

                PrintWriter writer =
                        new PrintWriter(socket.getOutputStream(), true);

                writer.println(jsonResponse);

                socket.close();
        }

            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }




