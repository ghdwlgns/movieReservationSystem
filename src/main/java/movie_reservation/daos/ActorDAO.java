package movie_reservation.daos;

import movie_reservation.entities.Actor;
import movie_reservation.entities.Movie;
import movie_reservation.types.Filmography;

import java.util.List;

public interface ActorDAO {
    void addActor(Actor actor);
    Actor findActor(String actorName);
    List<Actor> findActorsByMovie(Movie movie);
    List<Actor> findAllActors();
    void updateActor(String actorName, Filmography filmography);
    void removeActor(String actorName);
    void removeAllActors();
}
