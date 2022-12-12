package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.SeatNumber;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservedSeatDTO {
    private SeatNumber seatNumber;
    private ScreenDTO screen;
    private ReservationDTO reservation;
}
