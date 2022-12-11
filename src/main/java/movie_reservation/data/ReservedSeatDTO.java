package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.ReservedSeat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservedSeatDTO {
    private SeatDTO seat;
    private ScreenDTO screen;
    private ReservationDTO reservation;
}
