package database;

import entity.Cities;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CitiesBase {
    public void save(Cities newCities) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(newCities);
            transaction.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
