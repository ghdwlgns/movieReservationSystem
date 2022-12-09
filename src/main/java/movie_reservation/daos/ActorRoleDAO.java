package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import movie_reservation.entities.ActorRole;
import movie_reservation.types.Casting;

import java.util.List;

public interface ActorRoleDAO {
    void addActorRole(ActorRole actorRole);
    List<ActorRole> findActorRolesByCasting(Casting casting);
    BooleanExpression castingEq(Casting casting);
}
