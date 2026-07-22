package com.socket.endpoint.main;

import com.socket.endpoint.socket.EndpointServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        EndpointServer endpointServer=context.getBean("endpointServer",EndpointServer.class);
        endpointServer.startServer();
    }
}
