package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbConn {
    
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getInstance() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("currconvDbConn");
        }
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }
}
