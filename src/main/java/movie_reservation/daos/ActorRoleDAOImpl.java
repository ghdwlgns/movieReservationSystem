package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.Actor;
import movie_reservation.entities.ActorRole;
import movie_reservation.entities.QActorRole;
import movie_reservation.types.Casting;

import javax.persistence.*;
import java.util.List;

public class ActorRoleDAOImpl implements ActorRoleDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QActorRole qActorRole;

    public ActorRoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qActorRole = new QActorRole("actorRole");
    }

    @Override
    public void addActorRole(ActorRole actorRole) {
        entityManager.persist(actorRole);
    }

    @Override
    public List<ActorRole> findActorRolesByActor(Actor actor) {
        return queryFactory.selectFrom(qActorRole)
                .where(qActorRole.actor.eq(actor))
                .orderBy(qActorRole.movie.title.asc())
                .fetch();
    }

    @Override
    public List<ActorRole> findActorRolesByCasting(Casting casting) {
        return queryFactory.selectFrom(qActorRole)
                .where(qActorRole.castingAs.eq(casting))
                .fetch();
    }
}
