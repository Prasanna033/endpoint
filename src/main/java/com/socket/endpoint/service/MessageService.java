package com.socket.endpoint.service;

import com.socket.endpoint.dao.TransactionDetailsDao;
import com.socket.endpoint.enums.ResponseStatus;
import com.socket.endpoint.enums.TransactionType;
import com.socket.endpoint.messageRequests.RefundRequest;
import com.socket.endpoint.messageRequests.SalesRequest;
import com.socket.endpoint.messageRequests.VerifyRequest;
import com.socket.endpoint.messageResponse.SalesPostResponse;
import com.socket.endpoint.model.TransactionDetails;
import com.socket.endpoint.messageResponse.SchemeResponse;

public class MessageService {

    private TransactionDetailsDao transactionDetailsDao=new TransactionDetailsDao();

    public SalesPostResponse processRequest(SalesRequest request) {

        switch (request.getTransactionType()) {
            case SALE:
                return processSale(request);

            case VOID:
                return processVoid(request);

            default:
                SalesPostResponse response = new SalesPostResponse();
                response.setResponseCode("96");
                response.setResponseMessage("Invalid Transaction Type");
                return response;
        }
    }
        public SalesPostResponse processRefundRequest (RefundRequest request){
            return processRefund(request);
        }

        public SalesPostResponse processVerifyRequest(VerifyRequest request){
            return processVerify(request);
        }

        private SalesPostResponse processSale(SalesRequest request){
        SalesPostResponse response = new SalesPostResponse();

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

        transactionDetails.setTransactionId(request.getTransactionId());

        SchemeProcessor schemeProcessor=new SchemeProcessor();
        AbstractScheme abstractScheme=null;

        switch (request.getCardEntity().getScheme()){
            case VISA:
                abstractScheme=new VisaSchemeImpl(schemeProcessor);
                break;

            case MASTERCARD:
                abstractScheme=new MastercardSchemeImpl(schemeProcessor);
                break;

            default:
                break;
        }
        SchemeResponse schemeResponse=null;
        if(abstractScheme !=null){
            schemeResponse=abstractScheme.processSale(request);
        }
        if (schemeResponse !=null){
            transactionDetails.setResponseCode(schemeResponse.getResponseCode());

            transactionDetails.setResponseStatus(schemeResponse.getResponseStatus());
        }


        transactionDetailsDao.save(transactionDetails);

        response.setTransactionId(request.getTransactionId());

        response.setResponseCode(ResponseStatus.AUTHORISED.getResponseCode().getStatusCode());
        response.setResponseMessage(ResponseStatus.AUTHORISED.getStatusMessage());

        return response;
    }

    private SalesPostResponse processRefund(RefundRequest request){
        SalesPostResponse response = new SalesPostResponse();

        TransactionDetails existingTransaction=transactionDetailsDao.findByGatewayReference(request.getGatewayReference());
        if (existingTransaction==null) {
            response.setResponseCode(ResponseStatus.TRANSACTION_NOT_FOUND.getResponseCode().getStatusCode());
            response.setResponseMessage(ResponseStatus.TRANSACTION_NOT_FOUND.getStatusMessage());
            return response;
        }

        response.setTransactionId(request.getTransactionId());

        response.setResponseCode(
                ResponseStatus.AUTHORISED
                        .getResponseCode()
                        .getStatusCode());

        response.setResponseMessage(
                ResponseStatus.AUTHORISED.getStatusMessage());

        TransactionDetails transactionDetails = new TransactionDetails();

        transactionDetails.setMerchantId(existingTransaction.getMerchantId());
        transactionDetails.setGatewayReference(existingTransaction.getGatewayReference());
        transactionDetails.setRecurrenceFlag(existingTransaction.getRecurrenceFlag());

        transactionDetails.setAmount(existingTransaction.getAmount());
        transactionDetails.setCurrencyCode(existingTransaction.getCurrencyCode());
        transactionDetails.setCashback(existingTransaction.getCashback());

        transactionDetails.setCardNumber(existingTransaction.getCardNumber());
        transactionDetails.setCardExpiry(existingTransaction.getCardExpiry());
        transactionDetails.setCvv(existingTransaction.getCvv());
        transactionDetails.setScheme(existingTransaction.getScheme());

        transactionDetails.setSubMerchantId(existingTransaction.getSubMerchantId());
        transactionDetails.setMerchantName(existingTransaction.getMerchantName());

        transactionDetails.setStreet(existingTransaction.getStreet());

        transactionDetails.setCity(existingTransaction.getCity());

        transactionDetails.setState(existingTransaction.getState());

        transactionDetails.setCustomerServiceNumber(existingTransaction.getCustomerServiceNumber());

        transactionDetails.setPostalCode(existingTransaction.getPostalCode());

        transactionDetails.setEmailId(existingTransaction.getEmailId());

        transactionDetails.seteWallet(existingTransaction.geteWallet());
        transactionDetails.setTransactionType(TransactionType.REFUND.name());
        transactionDetails.setTransactionId(request.getTransactionId());
        transactionDetails.setParentTransactionId(existingTransaction.getTransactionId());

        transactionDetails.setResponseCode(ResponseStatus.AUTHORISED.getResponseCode().getStatusCode());
        transactionDetails.setResponseStatus(ResponseStatus.AUTHORISED.getStatusMessage());

        transactionDetailsDao.save(transactionDetails);

        return response;
    }

    private SalesPostResponse processVerify(VerifyRequest request){
        SalesPostResponse response=new SalesPostResponse();

        TransactionDetails existingTransaction=transactionDetailsDao.findByGatewayReference(request.getGatewayReference());

        if (existingTransaction==null){
            response.setResponseCode("01");
            response.setResponseMessage("Transaction not found");
            return response;
        }
        response.setTransactionId(existingTransaction.getGatewayReference());
        response.setResponseCode("00");
        response.setResponseMessage("Transaction Verified");

        return response;
    }

    private SalesPostResponse processVoid(SalesRequest request){
        SalesPostResponse response = new SalesPostResponse();

        TransactionDetails existingTransaction=transactionDetailsDao.findByGatewayReference(request.getGatewayReference());

        if (existingTransaction==null){
            response.setResponseCode("01");
            response.setResponseMessage("Transaction not found");
            return response;
        }
        response.setTransactionId(existingTransaction.getGatewayReference());
        response.setResponseCode("00");
        response.setResponseMessage("Transaction Voided Successfully");

        return response;
    }
}
