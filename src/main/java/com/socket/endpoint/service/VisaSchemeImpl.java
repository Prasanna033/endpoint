package com.socket.endpoint.service;

import com.socket.endpoint.messageresponse.SchemeResponse;

public class VisaSchemeImpl extends AbstractScheme{

    public VisaSchemeImpl(SchemeProcessor schemeProcessor) {
        super(schemeProcessor);
    }
    @Override
    public SchemeResponse processSale(){
        SchemeResponse response=super.processSale();

        response.setResponseStatus("VISA AUTHORISED");
        return response;
    }
}
