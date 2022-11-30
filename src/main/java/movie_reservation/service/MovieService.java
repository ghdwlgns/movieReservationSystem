package movie_reservation.service;

import movie_reservation.entities.Movie;
import movie_reservation.entities.Reservation;

public interface MovieService {
    void addMovie(Movie movie);
    void findMovie();
    void findMovies();
    void updateMovies();
    void removeMovie();

}
