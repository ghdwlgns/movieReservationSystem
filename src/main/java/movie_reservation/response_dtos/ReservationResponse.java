package movie_reservation.data;

import movie_reservation.entities.Reservation;
import movie_reservation.entities.ReservedSeat;
import movie_reservation.types.State;

import java.util.ArrayList;
import java.util.List;

public class ReservationDTO {
    private UserDTO user;
    private State state;
    private ScreenDTO screen;
    private List<ReservedSeatDTO> reservedSeats;

    public ReservationDTO(Reservation reservation) {
        user = new UserDTO(reservation.getUser());
        state = reservation.getState();
        screen = new ScreenDTO(reservation.getScreen());
        init(reservation.getReservedSeats());
    }

    private void init(List<ReservedSeat> reservedSeats) {
        this.reservedSeats = new ArrayList<>();

        for(ReservedSeat i : reservedSeats) {
            ReservedSeatDTO reservedSeat = new ReservedSeatDTO(i);
            this.reservedSeats.add(reservedSeat);
        }
    }

    public UserDTO getUser() {
        return user;
    }

    public State getState() {
        return state;
    }

    public ScreenDTO getScreen() {
        return screen;
    }

    public List<ReservedSeatDTO> getReservedSeats() {
        return reservedSeats;
    }
}
