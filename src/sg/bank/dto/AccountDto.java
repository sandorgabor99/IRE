package sg.bank.dto;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sg.bank.entity.Account;
import sg.bank.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.ArrayList;

public class AccountDto {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private ArrayList<Account> list = new ArrayList();

    public void createNewAccount(Account entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Crating new account : \n" + entity.toString());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nCreating new account exception: \n " + e.getMessage());
        }
    }

    public ArrayList<Account> getAllAccounts() {
        if (!list.isEmpty()) {
            list.clear();
        }

        try {
            Query query = session.createQuery("Select a from Account a");
            list = (ArrayList<Account>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getAllAccounts exception: \n " + e.getMessage());
        }
        return list;
    }

    public Account checkIfUserHasNotAccount(int id) {
        Account account = new Account();
        try {
            Query query = session.createQuery("Select a from Account a where a.user.id =: id");
            query.setParameter("id", id);
            account = (Account) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("\n getAllAccounts exception: \n " + e.getMessage());
        }
        if(account == null){
            return null;
        }
        return account;
    }

    public Account getAccountById(int id) {
        if (!list.isEmpty()) {
            list.clear();
        }
        try {
            Query query = session.createQuery("Select a from Account a where a.id =: id");
            query.setParameter("id", id);
            list = (ArrayList<Account>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getAccountById exception: \n " + e.getMessage());
        }
        if(list.isEmpty()){
            return null;
        }

        return list.get(0);
    }

    public Account getAccountByAccountNumber(String accnumber) {
        if (!list.isEmpty()) {
            list.clear();
        }
        try {
            Query query = session.createQuery("Select a from Account a where a.accountnumber =: accnumber");
            query.setParameter("accnumber", accnumber);
            list = (ArrayList<Account>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getAccountById exception: \n " + e.getMessage());
        }
        if(list.isEmpty()){
            return null;
        }

        return list.get(0);
    }

    public void updateAccount(Account entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Updating new Account : \n" + entity.toString());
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nUpdating new Account exception: \n " + e.getMessage());
        }
    }

    public void deleteAccount(Account entity) {
        System.out.println("Account user : " + entity.getId() + " " + entity.getAccountnumber());
        entity.setActiveAccount(false);
        //Majd át kell vezetni a kifizetésre
        entity.setBalance(0);
        updateAccount(entity);
    }


}
