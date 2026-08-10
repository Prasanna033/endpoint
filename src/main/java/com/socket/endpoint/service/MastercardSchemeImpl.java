package com.socket.endpoint.service;

import com.socket.endpoint.messageRequests.SalesRequest;
import com.socket.endpoint.messageResponse.SchemeResponse;

public class MastercardSchemeImpl extends AbstractScheme{

    public MastercardSchemeImpl(SchemeProcessor schemeProcessor) {
        super(schemeProcessor);
    }
    @Override
    public SchemeResponse processSale(SalesRequest salesRequest){
        SchemeResponse response=super.processSale(salesRequest);

        response.setResponseStatus("MASTERCARD AUTHORISED");
        return response;
    }
}
