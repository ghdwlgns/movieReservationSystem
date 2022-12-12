package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.Seat;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SeatDTO {
    private TheaterDTO theater;
    private SeatNumber seatNumber;
    private SeatState state;

    public Seat toEntity() {
        return new Seat(theater.toEntity(), seatNumber);
    }
}
