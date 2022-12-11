package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.Actor;
import movie_reservation.entities.QActor;

import javax.persistence.*;
import java.util.List;

public class ActorDAOImpl implements ActorDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QActor qActor;

    public ActorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qActor = new QActor("actor");
    }

    @Override
    public void addActor(Actor actor) {
        entityManager.persist(actor);
    }

    @Override
    public Actor findActor(String actorName) {
        return queryFactory.selectFrom(qActor)
                .where(qActor.name.eq(actorName))
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
        Actor actorFind = queryFactory.selectFrom(qActor)
                .where(qActor.name.eq(actorName))
                .fetchFirst();

        entityManager.remove(actorFind);
    }

    @Override
    public void removeAllActors() {
        List<Actor> actorList = queryFactory.selectFrom(qActor)
                .fetch();

        for(Actor actor : actorList) {
            entityManager.remove(actor);
        }
    }

}
