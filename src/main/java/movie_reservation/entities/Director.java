package movie_reservation.entities;

import movie_reservation.types.Filmography;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Director")
public class Director extends MovieStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIRECTOR_ID")
    private Long id;
    private String birthPlace;
    @OneToMany(mappedBy = "director")
    private List<Movie> moviesDirected;

    public Director() {

    }

    public Director(String name, Date birth, Filmography filmography, String birthPlace) {
        setName(name);
        setBirth(birth);
        setFilmography(filmography);
        this.birthPlace = birthPlace;
    }

    public List<Movie> getMoviesDirected() {
        return moviesDirected;
    }
}
