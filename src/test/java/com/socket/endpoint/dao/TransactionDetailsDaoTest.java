package com.socket.endpoint.dao;

import com.socket.endpoint.model.TransactionDetails;
import com.socket.endpoint.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TransactionDetailsDaoTest {

    @Test
    void testFindByGatewayReference() {

        SessionFactory factory = mock(SessionFactory.class);
        Session session = mock(Session.class);
        Query<TransactionDetails> query = mock(Query.class);

        TransactionDetails details = new TransactionDetails();

        when(factory.openSession()).thenReturn(session);
        when(session.createQuery(anyString(), eq(TransactionDetails.class)))
                .thenReturn(query);

        when(query.setParameter(anyString(), any()))
                .thenReturn(query);

        when(query.setMaxResults(1))
                .thenReturn(query);

        when(query.uniqueResult())
                .thenReturn(details);

        try (MockedStatic<HibernateUtil> mocked =
                     mockStatic(HibernateUtil.class)) {

            mocked.when(HibernateUtil::getSessionFactory)
                    .thenReturn(factory);

            TransactionDetailsDao dao = new TransactionDetailsDao();

            TransactionDetails result =
                    dao.findByGatewayReference("G001");

            assertNotNull(result);

            verify(query).setMaxResults(1);
            verify(query).uniqueResult();
            verify(session).close();
        }
    }
    @Test
    void testSaveTransactionDetails() {

        SessionFactory factory = mock(SessionFactory.class);
        Session session = mock(Session.class);
        Transaction transaction = mock(Transaction.class);

        try (MockedStatic<HibernateUtil> mocked =
                     mockStatic(HibernateUtil.class)) {

            mocked.when(HibernateUtil::getSessionFactory)
                    .thenReturn(factory);

            when(factory.openSession()).thenReturn(session);
            when(session.beginTransaction()).thenReturn(transaction);

            TransactionDetails details = new TransactionDetails();

            TransactionDetailsDao dao = new TransactionDetailsDao();
            dao.save(details);

            verify(session).beginTransaction();
            verify(session).persist(details);
            verify(transaction).commit();
            verify(session).close();
        }
    }
}