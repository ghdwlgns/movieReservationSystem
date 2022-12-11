package movie_reservation.daos;

import movie_reservation.entities.Actor;
import movie_reservation.entities.ActorRole;
import movie_reservation.types.Casting;

import java.util.List;

public interface ActorRoleDAO {
    void addActorRole(ActorRole actorRole);
    List<ActorRole> findActorRolesByActor(Actor actor);
    List<ActorRole> findActorRolesByCasting(Casting casting);
}
