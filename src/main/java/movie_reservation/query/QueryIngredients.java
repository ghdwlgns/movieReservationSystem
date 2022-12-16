package movie_reservation.query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QueryIngredients {
    private final EntityManagerFactory entityManagerFactory;
    private static QueryIngredients instance = new QueryIngredients();

    private QueryIngredients() {
        entityManagerFactory = Persistence.createEntityManagerFactory("movie_reservation");
    }

    public static QueryIngredients getInstance() {
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void emfClose() {
        entityManagerFactory.close();
    }
}
