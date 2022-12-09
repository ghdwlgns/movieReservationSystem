package movie_reservation.daos;

import movie_reservation.entities.Screen;
import movie_reservation.entities.Theater;

import java.util.List;

public interface ScreenDAO {
    void addScreen(Screen screen);
    List<Screen> findScreens();
    List<Screen> findScreensByStartTime(String startTime);
}
