package movie_reservation.entities;

import movie_reservation.types.Filmography;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public Director(String name, LocalDate birth, String birthPlace) {
        super(name, birth);
        this.birthPlace = birthPlace;
        moviesDirected = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return super.getName();
    }

    public LocalDate getBirth() {
        return super.getBirth();
    }

    public List<Filmography> getFilmographyList() {
        return super.getFilmographyList();
    }

    public List<Movie> getMoviesDirected() {
        return moviesDirected;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
}
