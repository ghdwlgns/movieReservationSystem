package movie_reservation.data;

import movie_reservation.entities.ActorRole;
import movie_reservation.types.Casting;

public class ActorRoleDTO {
    private ActorDTO actor;
    private MovieDTO movie;
    private Casting castingAs;

    public ActorRoleDTO(ActorRole actorRole) {
        actor = new ActorDTO(actorRole.getActor());
        movie = new MovieDTO(actorRole.getMovie());
        castingAs = actorRole.getCastingAs();
    }

    public ActorDTO getActor() {
        return actor;
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public Casting getCastingAs() {
        return castingAs;
    }
}
