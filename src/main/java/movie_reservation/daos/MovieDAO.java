package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import movie_reservation.entities.Movie;
import movie_reservation.types.Genre;

import java.util.List;

public interface MovieDAO {
    void addMovie(Movie movie);
    Movie findMovie(String movieTitle);
    List<Movie> findAllMovies();
    List<Movie> findMoviesByGenre(Genre genre);
    void updateMovieReleaseDate(String movieTitle, String releaseDateUpdate);
    void removeMovie(String movieTitle);
    void removeAllMovies();
    BooleanExpression releaseDateContains(String year);
    BooleanExpression genreEq(Genre genre);
}