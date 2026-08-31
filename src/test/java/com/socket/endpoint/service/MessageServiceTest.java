package com.socket.endpoint.service;

import com.socket.endpoint.dao.TransactionDetailsDao;
import com.socket.endpoint.enums.EWallet;
import com.socket.endpoint.enums.Scheme;
import com.socket.endpoint.enums.TransactionType;
import com.socket.endpoint.messagerequest.RefundRequest;
import com.socket.endpoint.messagerequest.SalesRequest;
import com.socket.endpoint.messagerequest.VerifyRequest;
import com.socket.endpoint.messageresponse.SalesPostResponse;
import com.socket.endpoint.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    @Test
    void processSale() {

        try (MockedConstruction<TransactionDetailsDao> ignored =
                     mockConstruction(TransactionDetailsDao.class)) {

            MessageService service = new MessageService();

            SalesRequest request = mock(SalesRequest.class);
            MoneyEntity money = mock(MoneyEntity.class);
            CardEntity card = mock(CardEntity.class);
            AcceptorDetails acceptor = mock(AcceptorDetails.class);

            when(request.getTransactionType()).thenReturn(TransactionType.SALE);
            when(request.getMerchantId()).thenReturn("M001");
            when(request.getGatewayReference()).thenReturn("G001");
            when(request.getRecurrenceFlag()).thenReturn("N");
            when(request.getTransactionId()).thenReturn("T001");

            when(request.getMoneyEntity()).thenReturn(money);
            when(money.getAmount()).thenReturn(100.0);
            when(money.getCurrencyCode()).thenReturn("INR");
            when(money.getCashback()).thenReturn(0.0);

            when(request.getCardEntity()).thenReturn(card);
            when(card.getCardNumber()).thenReturn("4111111111111111");
            when(card.getCardExpiry()).thenReturn("12/30");
            when(card.getCvv()).thenReturn("123");
            when(card.getScheme()).thenReturn(Scheme.VISA);

            when(request.getAcceptorDetails()).thenReturn(acceptor);
            when(acceptor.getSubMerchantId()).thenReturn("SUB001");
            when(acceptor.getMerchantName()).thenReturn("TEST");

            MerchantContactDetails contactDetails = mock(MerchantContactDetails.class);

            when(acceptor.getMerchantContactDetails()).thenReturn(contactDetails);

            when(contactDetails.getStreet()).thenReturn("Test Street");
            when(contactDetails.getCity()).thenReturn("Test City");
            when(contactDetails.getState()).thenReturn("Test State");
            when(contactDetails.getPostalCode()).thenReturn("123456");
            when(contactDetails.getCustomerServiceNumber()).thenReturn("9999999999");
            when(contactDetails.getEmailId()).thenReturn("test@test.com");

            when(request.geteWallet()).thenReturn(EWallet.UNKNOWN);
            SalesPostResponse response = service.processRequest(request);
            assertNotNull(response);
        }
    }

    @Test
    void processRefundRequestTransactionNotFound() {

        try (MockedConstruction<TransactionDetailsDao> ignored =
                     mockConstruction(TransactionDetailsDao.class,
                             (mock, context) ->
                                     when(mock.findByGatewayReference(anyString()))
                                             .thenReturn(null))) {

            MessageService service = new MessageService();

            RefundRequest request = mock(RefundRequest.class);
            when(request.getGatewayReference()).thenReturn("G001");

            SalesPostResponse response =
                    service.processRefundRequest(request);

            assertNotNull(response);
            assertEquals("04", response.getResponseCode());
        }
    }

    @Test
    void processRefundRequestTransactionFound() {

        try (MockedConstruction<TransactionDetailsDao> ignored =
                     mockConstruction(TransactionDetailsDao.class,
                             (mock, context) -> {

                                 TransactionDetails transaction =
                                         mock(TransactionDetails.class);

                                 when(mock.findByGatewayReference(anyString()))
                                         .thenReturn(transaction);
                             })) {

            MessageService service = new MessageService();

            RefundRequest request = mock(RefundRequest.class);

            when(request.getGatewayReference()).thenReturn("G001");
            when(request.getTransactionId()).thenReturn("T002");

            SalesPostResponse response =
                    service.processRefundRequest(request);

            assertNotNull(response);
        }
    }

    @Test
    void processVerifyRequestTransactionNotFound() {

        try (MockedConstruction<TransactionDetailsDao> ignored =
                     mockConstruction(TransactionDetailsDao.class,
                             (mock, context) ->
                                     when(mock.findByGatewayReference(anyString()))
                                             .thenReturn(null))) {

            MessageService service = new MessageService();

            VerifyRequest request = mock(VerifyRequest.class);
            when(request.getGatewayReference()).thenReturn("G001");

            SalesPostResponse response =
                    service.processVerifyRequest(request);

            assertNotNull(response);
            assertEquals("01", response.getResponseCode());
        }
    }

    @Test
    void processVerifyRequestTransactionFound() {

        try (MockedConstruction<TransactionDetailsDao> ignored =
                     mockConstruction(TransactionDetailsDao.class,
                             (mock, context) -> {

                                 TransactionDetails transaction =
                                         mock(TransactionDetails.class);

                                 when(transaction.getGatewayReference())
                                         .thenReturn("G001");

                                 when(mock.findByGatewayReference(anyString()))
                                         .thenReturn(transaction);
                             })) {

            MessageService service = new MessageService();

            VerifyRequest request = mock(VerifyRequest.class);
            when(request.getGatewayReference()).thenReturn("G001");

            SalesPostResponse response =
                    service.processVerifyRequest(request);

            assertNotNull(response);
            assertEquals("00", response.getResponseCode());
        }
    }

    @Test
    void processVoidTransactionNotFound() {

        try (MockedConstruction<TransactionDetailsDao> ignored =
                     mockConstruction(TransactionDetailsDao.class,
                             (mock, context) ->
                                     when(mock.findByGatewayReference(anyString()))
                                             .thenReturn(null))) {

            MessageService service = new MessageService();

            SalesRequest request = mock(SalesRequest.class);

            when(request.getTransactionType()).thenReturn(TransactionType.VOID);
            when(request.getGatewayReference()).thenReturn("G001");

            SalesPostResponse response =
                    service.processRequest(request);

            assertNotNull(response);
            assertEquals("01", response.getResponseCode());
        }
    }

    @Test
    void processVoidTransactionFound() {

        try (MockedConstruction<TransactionDetailsDao> ignored =
                     mockConstruction(TransactionDetailsDao.class,
                             (mock, context) -> {

                                 TransactionDetails transaction =
                                         mock(TransactionDetails.class);

                                 when(transaction.getGatewayReference())
                                         .thenReturn("G001");

                                 when(mock.findByGatewayReference(anyString()))
                                         .thenReturn(transaction);
                             })) {

            MessageService service = new MessageService();

            SalesRequest request = mock(SalesRequest.class);

            when(request.getTransactionType()).thenReturn(TransactionType.VOID);
            when(request.getGatewayReference()).thenReturn("G001");

            SalesPostResponse response =
                    service.processRequest(request);

            assertNotNull(response);
            assertEquals("00", response.getResponseCode());
        }
    }
}