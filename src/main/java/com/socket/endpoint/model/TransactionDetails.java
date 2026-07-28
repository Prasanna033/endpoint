package com.socket.endpoint.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_details")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "merchant_id")
    private String merchantId;
    @Column(name = "gateway_Reference")
    private String gatewayReference;
    @Column(name = "recurrence_Flag")
    private String recurrenceFlag;

    @Column(name = "amount")
    private Double amount;
    @Column(name = "currency_Code")
    private String currencyCode;
    @Column(name = "cashback")
    private Double cashback;

    @Column(name = "card_Number")
    private String cardNumber;
    @Column(name = "card_Expiry")
    private String cardExpiry;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "scheme")
    private String scheme;

    @Column(name = "subMerchant_id")
    private String subMerchantId;
    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "customerService_Number")
    private String customerServiceNumber;
    @Column(name = "postal_Code")
    private String postalCode;
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "eWallet")
    private String eWallet;
    @Column(name = "transaction_Type")
    private String transactionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getRecurrenceFlag() {
        return recurrenceFlag;
    }

    public void setRecurrenceFlag(String recurrenceFlag) {
        this.recurrenceFlag = recurrenceFlag;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getCashback() {
        return cashback;
    }

    public void setCashback(Double cashback) {
        this.cashback = cashback;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCustomerServiceNumber() {
        return customerServiceNumber;
    }

    public void setCustomerServiceNumber(String customerServiceNumber) {
        this.customerServiceNumber = customerServiceNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String geteWallet() {
        return eWallet;
    }

    public void seteWallet(String eWallet) {
        this.eWallet = eWallet;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
