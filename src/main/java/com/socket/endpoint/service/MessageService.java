package com.socket.endpoint.service;

import com.socket.endpoint.dao.TransactionDetailsDao;
import com.socket.endpoint.model.SalesPostResponse;
import com.socket.endpoint.model.SalesRequest;
import com.socket.endpoint.model.TransactionDetails;

public class MessageService {

    private TransactionDetailsDao transactionDetailsDao=new TransactionDetailsDao();

    public SalesPostResponse processRequest(SalesRequest request) {

        SalesPostResponse response = new SalesPostResponse();

        response.setTransactionId("TXN100001");
        response.setResponseCode("00");
        response.setResponseMessage("Transaction Successful");

        TransactionDetails transactionDetails = new TransactionDetails();

        transactionDetails.setMerchantId(request.getMerchantId());
        transactionDetails.setGatewayReference(request.getGatewayReference());
        transactionDetails.setRecurrenceFlag(request.getRecurrenceFlag());

        transactionDetails.setAmount(request.getMoneyEntity().getAmount());
        transactionDetails.setCurrencyCode(request.getMoneyEntity().getCurrencyCode());
        transactionDetails.setCashback(request.getMoneyEntity().getCashback());

        transactionDetails.setCardNumber(request.getCardEntity().getCardNumber());
        transactionDetails.setCardExpiry(request.getCardEntity().getCardExpiry());
        transactionDetails.setCvv(request.getCardEntity().getCvv());
        transactionDetails.setScheme(request.getCardEntity().getScheme().name());

        transactionDetails.setSubMerchantId(request.getAcceptorDetails().getSubMerchantId());
        transactionDetails.setMerchantName(request.getAcceptorDetails().getMerchantName());

        transactionDetails.setStreet(request.getAcceptorDetails()
                .getMerchantContactDetails().getStreet());

        transactionDetails.setCity(request.getAcceptorDetails()
                .getMerchantContactDetails().getCity());

        transactionDetails.setState(request.getAcceptorDetails()
                .getMerchantContactDetails().getState());

        transactionDetails.setCustomerServiceNumber(request.getAcceptorDetails()
                .getMerchantContactDetails().getCustomerServiceNumber());

        transactionDetails.setPostalCode(request.getAcceptorDetails()
                .getMerchantContactDetails().getPostalCode());

        transactionDetails.setEmailId(request.getAcceptorDetails()
                .getMerchantContactDetails().getEmailId());

        transactionDetails.seteWallet(request.geteWallet().name());
        transactionDetails.setTransactionType(request.getTransactionType().name());

        transactionDetailsDao.save(transactionDetails);

        return response;
    }

}
