package movie_reservation.services;

import movie_reservation.data.ScreenDTO;
import movie_reservation.data.TheaterDTO;
import movie_reservation.request_dtos.MovieRequest;
import movie_reservation.request_dtos.ScreenRequest;
import movie_reservation.request_dtos.TheaterRequest;
import movie_reservation.response_dtos.ScreenResponse;
import movie_reservation.response_dtos.TheaterResponse;

import java.util.List;

public interface TheaterScreenService {
    // 라고 쓰고 사실상 Theater + Screen Service이다
    void registerTheater(TheaterDTO theaterDTO);
    void registerScreen(ScreenDTO screenDTO);
    TheaterResponse findTheaterByTheater(TheaterRequest theaterRequest);
    List<TheaterResponse> findTheatersByFloor(String floor);
    List<ScreenResponse> findScreenByMovie(MovieRequest movie);
    void removeAllTheater();
    void removeScreen(ScreenRequest screenRequest);
    void removeAllScreen();
}
