package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import movie_reservation.entities.Actor;

import java.util.List;

public interface ActorDAO {
    void addActor(Actor actor);
    Actor findActor(String actorName);
    List<Actor> findAllActors();
    void removeActor(String actorName);
    void removeAllActors();
    BooleanExpression actorNameEq(String actorName);
}
