package movie_reservation.types;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Filmography {
    String movieParticipated;
    @Enumerated(EnumType.STRING)
    private Job job;
    private Casting casting;

    public Filmography() {

    }

    public Filmography(String movieParticipated, Job job, Casting casting) {
        this.movieParticipated = movieParticipated;
        this.job = job;
        this.casting = casting;
    }

    public String getMovieParticipated() {
        return movieParticipated;
    }

    public Casting getCasting() {
        return casting;
    }

    public Job getJob() {
        return job;
    }

    @Override
    public String toString() {
        return "영화제목: " + movieParticipated + " " + casting;
    }
}
