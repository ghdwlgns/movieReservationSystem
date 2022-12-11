package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.ReservedSeat;
import movie_reservation.entities.Seat;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SeatDTO {
    private TheaterDTO theater;
    private SeatNumber seatNumber;
    private SeatState state;
    private List<ReservedSeatDTO> reservedSeats;
}
