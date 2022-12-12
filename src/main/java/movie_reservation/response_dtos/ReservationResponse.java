package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.data.ScreenDTO;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.State;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationResponse {
    private String userName;
    private State state;
    private ScreenDTO screen;
    private List<SeatNumber> reservedSeats;
}
