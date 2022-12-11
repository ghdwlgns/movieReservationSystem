package movie_reservation.daos;

import movie_reservation.entities.Theater;

import java.util.List;

public interface TheaterDAO {
    void addTheater(Theater theater);
    Theater findTheater(String floor, String theaterName);
    List<Theater> findAllTheaters();
    List<Theater> findTheatersByFloor(String floor);
    void removeTheater(String floor, String theaterName);
    void removeTheatersByFloor(String floor);
    void removeAllTheaters();
}
