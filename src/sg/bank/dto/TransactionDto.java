package sg.bank.dto;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sg.bank.entity.Account;
import sg.bank.entity.Transactions;
import sg.bank.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.ArrayList;

public class TransactionDto {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private ArrayList<Transactions> list = new ArrayList();

    public void createNewTransaction(Transactions entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Crating new transaction : \n" + entity.toString());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nCreating new transaction exception: \n " + e.getMessage());
        }
    }

    public ArrayList<Transactions> getAllTransactions() {
        if (!list.isEmpty()) {
            list.clear();
        }

        try {
            Query query = session.createQuery("Select t from Transactions t");
            list = (ArrayList<Transactions>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getAllTransactions exception: \n " + e.getMessage());
        }
        return list;
    }

    public void updateTransaction(Transactions entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Updating new Transaction : \n" + entity.toString());
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nUpdating new Transaction exception: \n " + e.getMessage());
        }
    }



}
