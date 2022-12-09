package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.Actor;
import movie_reservation.entities.QActor;

import javax.persistence.*;
import java.util.List;

public class ActorDAOImpl implements ActorDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private JPAQueryFactory queryFactory;
    private QActor qActor;

    public ActorDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("movie_reservation");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        queryFactory = new JPAQueryFactory(entityManager);
        qActor = new QActor("actor");
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
    public void addActor(Actor actor) {
        try {
            entityTransaction.begin();

            entityManager.persist(actor);

            flushChange();
            entityTransaction.commit();
        } catch (RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public Actor findActor(String actorName) {
        return queryFactory.selectFrom(qActor)
                .where(actorNameEq(actorName))
                .fetchFirst();
    }

    @Override
    public List<Actor> findAllActors() {
        return queryFactory.selectFrom(qActor)
                .orderBy(qActor.name.asc())
                .fetch();
    }

    @Override
    public void removeActor(String actorName) {
        try {
            entityTransaction.begin();

            Actor actorFind = queryFactory.selectFrom(qActor)
                    .where(actorNameEq(actorName))
                    .fetchFirst();

            entityManager.remove(actorFind);

            flushChange();
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.commit();
        }
    }

    @Override
    public void removeAllActors() {
        try {
            entityTransaction.begin();

            List<Actor> actorList = queryFactory.selectFrom(qActor)
                    .fetch();

            for(Actor actor : actorList)
                entityManager.remove(actor);

            flushChange();
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.commit();
        }
    }

    @Override
    public BooleanExpression actorNameEq(String actorName) {
        if(actorName == null || actorName.equals(""))
            return null;
        return QActor.actor.name.eq(actorName);
    }
}
