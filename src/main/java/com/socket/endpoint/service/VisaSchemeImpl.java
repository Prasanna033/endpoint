package com.socket.endpoint.service;

import com.socket.endpoint.messageRequests.SalesRequest;
import com.socket.endpoint.messageResponse.SchemeResponse;

public class VisaSchemeImpl extends AbstractScheme{

    public VisaSchemeImpl(SchemeProcessor schemeProcessor) {
        super(schemeProcessor);
    }
    @Override
    public SchemeResponse processSale(SalesRequest salesRequest){
        SchemeResponse response=super.processSale(salesRequest);

        response.setResponseStatus("VISA AUTHORISED");
        return response;
    }
}
