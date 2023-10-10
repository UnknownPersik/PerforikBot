package database;

import entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserBase {
    public User findByTelegramId(Long telegramId) {
        Transaction transaction;
        User user;
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

    public User findCities(Long telegramId) {
        Transaction transaction;
        User user;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("SELECT b FROM entity.User b WHERE b.id = :id", User.class);
            query.setParameter("id", telegramId);
            user = query.uniqueResult();
            Hibernate.initialize(user.getCities());
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
        return user;
    }

    public void save(User newUser) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}