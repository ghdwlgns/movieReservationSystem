package movie_reservation.daos;

import movie_reservation.entities.ActorRole;
import movie_reservation.entities.Director;
import movie_reservation.entities.Movie;
import movie_reservation.types.Genre;

import java.util.List;

public interface MovieDAO {
    void addMovie(Movie movie);
    Movie findMovie(String movieTitle);
    List<Movie> findAllMovies();
    List<Movie> findMoviesByGenre(Genre genre);
    List<Movie> findMoviesBy(Director director, ActorRole actorRole, String year);
    void updateMovieReleaseDate(String movieTitle, String releaseDateUpdate);
    void removeMovie(String movieTitle);
    void removeAllMovies();
}