package movie_reservation.data;

import movie_reservation.entities.ReservedSeat;
import movie_reservation.entities.Screen;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScreenDTO {
    private MovieDTO movie;
    private TheaterDTO theater;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<ReservedSeatDTO> reservedSeats;
    private ReservationDTO reservation;

    public ScreenDTO(Screen screen) {
        movie = new MovieDTO(screen.getMovie());
        theater = new TheaterDTO(screen.getTheater());
        startTime = screen.getStartTime();
        endTime = screen.getEndTime();
        reservation = new ReservationDTO(screen.getReservation());
        init(screen.getReservedSeats());
    }

    private void init(List<ReservedSeat> reservedSeats) {
        this.reservedSeats = new ArrayList<>();

        for(ReservedSeat i : reservedSeats) {
            ReservedSeatDTO reservedSeat = new ReservedSeatDTO(i);
            this.reservedSeats.add(reservedSeat);
        }
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public TheaterDTO getTheater() {
        return theater;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public List<ReservedSeatDTO> getReservedSeats() {
        return reservedSeats;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }
}
