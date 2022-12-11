package movie_reservation.data;

import movie_reservation.entities.Director;
import movie_reservation.entities.Movie;
import movie_reservation.types.Filmography;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DirectorDTO {
    private String name;
    private LocalDate birth;
    private List<Filmography> filmographyList;
    private String birthPlace;
    private List<MovieDTO> moviesDirected;

    public DirectorDTO(Director director) {
        name = director.getName();
        birth = director.getBirth();
        filmographyList = director.getFilmographyList();
        birthPlace = director.getBirthPlace();
        init(director.getMoviesDirected());
    }

    private void init(List<Movie> moviesDirected) {
        this.moviesDirected = new ArrayList<>();

        for(Movie i : moviesDirected) {
            MovieDTO movie = new MovieDTO(i);
            this.moviesDirected.add(movie);
        }
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

    public String getBirthPlace() {
        return birthPlace;
    }

    public List<MovieDTO> getMoviesDirected() {
        return moviesDirected;
    }
}
