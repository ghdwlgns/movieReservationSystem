package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SeatResponse {
    private TheaterResponse theater;
    private SeatNumber seatNumber;
    private SeatState state;

    @Override
    public String toString() {
        return seatNumber + ": " + state;
    }
}
