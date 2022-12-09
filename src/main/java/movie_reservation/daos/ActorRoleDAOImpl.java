package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.ActorRole;
import movie_reservation.entities.QActorRole;
import movie_reservation.types.Casting;

import javax.persistence.*;
import java.util.List;

public class ActorRoleDAOImpl implements ActorRoleDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private JPAQueryFactory queryFactory;
    private QActorRole qActorRole;

    public ActorRoleDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("movie_reservation");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        queryFactory = new JPAQueryFactory(entityManager);
        qActorRole = new QActorRole("actorRole");
    }

    private void flushChange() {
        entityManager.flush();
        entityManager.clear();
    }

    public void terminateDAO() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void addActorRole(ActorRole actorRole) {
        try {
            entityTransaction.begin();

            entityManager.persist(actorRole);

            flushChange();
            entityTransaction.commit();

        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }

    }

    @Override
    public List<ActorRole> findActorRolesByCasting(Casting casting) {
        return queryFactory.selectFrom(qActorRole)
                .where(castingEq(casting))
                .fetch();
    }

    @Override
    public BooleanExpression castingEq(Casting casting) {
        if(casting == null)
            return null;
        return QActorRole.actorRole.castingAs.eq(casting);
    }
}
