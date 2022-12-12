package movie_reservation.daos;

import movie_reservation.entities.Screen;
import movie_reservation.entities.Seat;
import movie_reservation.entities.Theater;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import java.util.List;

public interface SeatDAO {
    /*
     * 예약 좌석과 일반 좌석 둘 다 관리
     */
    void addSeat(Seat seat);
    Seat findSeatByTheaterAndSeatNumber(Theater theater, SeatNumber seatNumber);
    List<Seat> findSeatsByScreen(Screen screen);
    List<Seat> findSeats();
    void updateSeats(SeatNumber seatNumber, SeatState seatState);
    void removeSeat(Theater theater, SeatNumber seatNumber);
    void removeAllSeats();
}
