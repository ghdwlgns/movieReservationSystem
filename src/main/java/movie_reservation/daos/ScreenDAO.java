package movie_reservation.daos;

import movie_reservation.entities.Screen;

import java.time.LocalTime;
import java.util.List;

public interface ScreenDAO {
    void addScreen(Screen screen);
    List<Screen> findScreens();
    List<Screen> findScreensByStartTime(LocalTime startTime);
    List<Screen> findScreensByMovieTitle(String movieTitle);
    List<Screen> findScreenByMovieTitleAndStartTime(String movieTitle, LocalTime startTime);
    void removeScreen(String movieTitle, LocalTime startTime);
    void removeAllScreens();
}
