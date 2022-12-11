package movie_reservation.query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QueryIngredients {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public QueryIngredients() {
        entityManagerFactory = Persistence.createEntityManagerFactory("movie_reservation");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
