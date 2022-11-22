package movie_reservation.types;

import movie_reservation.entities.Movie;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Embeddable
public class Filmography {
    @ElementCollection
    private List<String> moviesParticipated;
    @Enumerated(EnumType.STRING)
    private Job job;

    public Filmography() {

    }

    public Filmography(List<String> moviesParticipated, Job job) {
        this.moviesParticipated = moviesParticipated;
        this.job = job;
    }
}
