package sg.bank.dto;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sg.bank.entity.Users;
import sg.bank.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.ArrayList;

public class UsersDto {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private ArrayList<Users> list = new ArrayList();

    public void createNewUser(Users entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Crating new user : \n" + entity.toString());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nCreating new user exception: \n " + e.getMessage());
        }
    }

    public ArrayList<Users> getAllUsers() {
        if (!list.isEmpty()) {
            list.clear();
        }

        try {
            Query query = session.createQuery("Select u from Users u");
            list = (ArrayList<Users>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getAllUsers exception: \n " + e.getMessage());
        }
        return list;
    }

    public Users getUserById(int id) {
        if (!list.isEmpty()) {
            list.clear();
        }
        try {
            Query query = session.createQuery("Select u from Users u where u.id =: id");
            query.setParameter("id", id);
            list = (ArrayList<Users>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getUserById exception: \n " + e.getMessage());
        }
        if(list.isEmpty()){
            return null;
        }

        return list.get(0);
    }

    public void updateUser(Users entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Updating new user : \n" + entity.toString());
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nUpdating new user exception: \n " + e.getMessage());
        }
    }

    public void deleteUser(Users entity) {
        System.out.println("Deleting user : " + entity.getId() + " " + entity.getFullname());
        Users updateEntity = entity;
        updateEntity.setActiveUser(false);
        updateUser(updateEntity);
    }

}
