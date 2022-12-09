package movie_reservation.data;

import movie_reservation.entities.ReservedSeat;
import movie_reservation.entities.Seat;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import java.util.ArrayList;
import java.util.List;

public class SeatDTO {
    private TheaterDTO theater;
    private SeatNumber seatNumber;
    private SeatState state;
    private List<ReservedSeatDTO> reservedSeats;

    public SeatDTO(Seat seat) {
        theater = new TheaterDTO(seat.getTheater());
        seatNumber = seat.getSeatNumber();
        state = seat.getState();
        init(seat.getReservedSeats());
    }

    private void init(List<ReservedSeat> reservedSeats) {
        this.reservedSeats = new ArrayList<>();

        for(ReservedSeat i : reservedSeats) {
            ReservedSeatDTO reservedSeat = new ReservedSeatDTO(i);
            this.reservedSeats.add(reservedSeat);
        }
    }

    public TheaterDTO getTheater() {
        return theater;
    }

    public SeatNumber getSeatNumber() {
        return seatNumber;
    }

    public SeatState getState() {
        return state;
    }

    public List<ReservedSeatDTO> getReservedSeats() {
        return reservedSeats;
    }
}
