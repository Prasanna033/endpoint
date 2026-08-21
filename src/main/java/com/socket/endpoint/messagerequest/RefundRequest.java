package com.socket.endpoint.messagerequest;

import com.socket.endpoint.enums.EWallet;
import com.socket.endpoint.enums.TransactionType;
import com.socket.endpoint.model.AcceptorDetails;
import com.socket.endpoint.model.CardEntity;
import com.socket.endpoint.model.MoneyEntity;

public class RefundRequest {

    private String merchantId;
    private String gatewayReference;
    private MoneyEntity moneyEntity;
    private String recurrenceFlag;
    private CardEntity cardEntity;
    private AcceptorDetails acceptorDetails;
    private EWallet eWallet;
    private TransactionType transactionType;
    private String transactionId;
    private String parentTransactionId;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getGatewayReference() {
        return gatewayReference;
    }

    public void setGatewayReference(String gatewayReference) {
        this.gatewayReference = gatewayReference;
    }

    public MoneyEntity getMoneyEntity() {
        return moneyEntity;
    }

    public void setMoneyEntity(MoneyEntity moneyEntity) {
        this.moneyEntity = moneyEntity;
    }

    public String getRecurrenceFlag() {
        return recurrenceFlag;
    }

    public void setRecurrenceFlag(String recurrenceFlag) {
        this.recurrenceFlag = recurrenceFlag;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public AcceptorDetails getAcceptorDetails() {
        return acceptorDetails;
    }

    public void setAcceptorDetails(AcceptorDetails acceptorDetails) {
        this.acceptorDetails = acceptorDetails;
    }

    public EWallet geteWallet() {
        return eWallet;
    }

    public void seteWallet(EWallet eWallet) {
        this.eWallet = eWallet;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }
}
