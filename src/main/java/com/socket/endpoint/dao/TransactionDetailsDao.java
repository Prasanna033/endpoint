package com.socket.endpoint.dao;

import com.socket.endpoint.model.TransactionDetails;
import com.socket.endpoint.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionDetailsDao {

    public void save(TransactionDetails transactionDetails){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();

        session.persist(transactionDetails);
        transaction.commit();
        session.close();
    }
    public TransactionDetails findByGatewayReference(String gatewayReference) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        TransactionDetails transactionDetails = session.createQuery(
                        "FROM TransactionDetails WHERE gatewayReference = :gatewayReference AND transactionType='SALE'",
                        TransactionDetails.class)
                .setParameter("gatewayReference", gatewayReference)
                .uniqueResult();

        session.close();

        return transactionDetails;
    }
}
