package dao;

import java.util.List;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CurrencyDao {
    
    public void persist(Currency currency) {
        EntityManager em = datasource.DbConn.getInstance();
        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }

    public Currency find(String code) {
        EntityManager em = datasource.DbConn.getInstance();
        return em.find(Currency.class, code);
    }

    public void update(Currency currency) {
        EntityManager em = datasource.DbConn.getInstance();
        em.getTransaction().begin();
        em.merge(currency);
        em.getTransaction().commit();
    }

    public void delete(String code) {
        EntityManager em = datasource.DbConn.getInstance();
        em.getTransaction().begin();
        Currency currency = em.find(Currency.class, code);
        em.remove(currency);
        em.getTransaction().commit();
    }

    public List<Currency> findAll() {
        EntityManager em = datasource.DbConn.getInstance();
        TypedQuery<Currency> query = em.createQuery("SELECT c FROM Currency c", Currency.class);
        return query.getResultList();
    }
}
