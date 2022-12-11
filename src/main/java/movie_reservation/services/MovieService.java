package services;

import movie_reservation.data.MovieDTO;

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
    void findMovies();
}
