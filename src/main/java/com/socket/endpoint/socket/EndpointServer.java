package com.socket.endpoint.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socket.endpoint.model.SalesPostResponse;
import com.socket.endpoint.model.SalesRequest;
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

                SalesRequest salesRequest =
                        objectMapper.readValue(jsonRequest,
                                SalesRequest.class);

                SalesPostResponse response =
                        messageService.processRequest(salesRequest);

                String jsonResponse =
                        objectMapper.writeValueAsString(response);

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




