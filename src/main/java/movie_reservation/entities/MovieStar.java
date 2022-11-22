package movie_reservation.entities;

import movie_reservation.types.Filmography;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class MovieStar {
    private String name;
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Embedded
    private Filmography filmography;

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public void setFilmography(Filmography filmography) {
        this.filmography = filmography;
    }
}
