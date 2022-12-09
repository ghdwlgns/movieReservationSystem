package movie_reservation.entities;

import movie_reservation.types.Filmography;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@MappedSuperclass
public abstract class MovieStar {
    private String name;
    @Temporal(TemporalType.DATE)
    private LocalDate birth;
    @ElementCollection
    private List<Filmography> filmographyList;

    protected MovieStar() {

    }

    public MovieStar(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
        filmographyList = new ArrayList<>();
    }

    public void addFilmography(Filmography filmography) {
        filmographyList.add(filmography);
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public List<Filmography> getFilmographyList() {
        return filmographyList;
    }
}
