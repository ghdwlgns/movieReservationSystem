package movie_reservation.data;

import movie_reservation.entities.ReservedSeat;

public class ReservedSeatDTO {
    private SeatDTO seat;
    private ScreenDTO screen;
    private ReservationDTO reservation;

    public ReservedSeatDTO(ReservedSeat reservedSeat) {
        seat = new SeatDTO(reservedSeat.getSeat());
        screen = new ScreenDTO(reservedSeat.getScreen());
        reservation = new ReservationDTO(reservedSeat.getReservation());
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public ScreenDTO getScreen() {
        return screen;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }
}
