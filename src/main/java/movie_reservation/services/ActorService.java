package movie_reservation.services;

import movie_reservation.data.ActorDTO;
import movie_reservation.response_dtos.ActorResponse;
import movie_reservation.types.Filmography;

import java.util.List;

public interface ActorService {
    void registerActor(ActorDTO actorDTO);
    ActorResponse findActor(String actorName);
    List<ActorResponse> findAllActors();
    void updateActorFilmography(String actorName, Filmography filmography);
    void removeActor(String actorName);
    void emClose();
}
