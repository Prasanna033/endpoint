package com.socket.endpoint.service;

import com.socket.endpoint.messageresponse.SchemeResponse;

public class MastercardSchemeImpl extends AbstractScheme{

    public MastercardSchemeImpl(SchemeProcessor schemeProcessor) {
        super(schemeProcessor);
    }
    @Override
    public SchemeResponse processSale(){
        SchemeResponse response=super.processSale();

        response.setResponseStatus("MASTERCARD AUTHORISED");
        return response;
    }
}
