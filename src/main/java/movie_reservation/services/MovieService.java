package movie_reservation.services;

import movie_reservation.data.MovieDTO;
import movie_reservation.request_dtos.MovieRequest;
import movie_reservation.response_dtos.MovieResponse;
import movie_reservation.types.Genre;

import java.util.List;

public interface MovieService {
    /**
     * 영화 등록
     * 감독으로 영화 조회
     * 배우로 영화 조회
     * 출시년도로 영화 조회
     * 3개 중 하나로 영화 조회
     * @param movie
     */
    void registerMovie(MovieDTO movie);
    MovieResponse findMovieByTitle(String movieTitle);
    List<MovieResponse> findMoviesBy(String directorName, String actorName, String year);
    List<MovieResponse> findMoviesByGenre(Genre genre);
    List<MovieResponse> findAllMovies();
    void removeMovie(MovieRequest movie);
}
