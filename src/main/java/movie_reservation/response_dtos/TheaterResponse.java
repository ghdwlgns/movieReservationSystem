package movie_reservation.data;

import movie_reservation.entities.Screen;
import movie_reservation.entities.Seat;
import movie_reservation.entities.Theater;

import java.util.ArrayList;
import java.util.List;

public class TheaterDTO {
    private String theaterName;
    private String floor;
    private List<ScreenDTO> screens;
    private List<SeatDTO> seats;

    public TheaterDTO(Theater theater) {
        theaterName = theater.getTheaterName();
        floor = theater.getFloor();
        initScreens(theater.getScreens());
        initSeats(theater.getSeats());
    }

    private void initScreens(List<Screen> screens) {
        this.screens = new ArrayList<>();

        for(Screen i : screens) {
            ScreenDTO screen = new ScreenDTO(i);
            this.screens.add(screen);
        }
    }

    private void initSeats(List<Seat> seats) {
        this.seats = new ArrayList<>();

        for(Seat i : seats) {
            SeatDTO seat = new SeatDTO(i);
            this.seats.add(seat);
        }
    }

    public String getTheaterName() {
        return theaterName;
    }

    public String getFloor() {
        return floor;
    }

    public List<ScreenDTO> getScreens() {
        return screens;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }
}
