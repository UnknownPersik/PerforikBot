package database;

import database.HibernateUtil;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserBase {
    public User findByTelegramId(Long telegramId) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("SELECT b FROM entity.User b WHERE b.id = :id", User.class);
            query.setParameter("id", telegramId);
            user = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
        return user;
    }

    public User save(User newUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
        return newUser;
    }

    public User update(User oldUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(oldUser);
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
        return oldUser;
    }


}