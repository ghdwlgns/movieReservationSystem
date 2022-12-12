package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.State;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationDTO {
    private String userName;
    private State state;
    private ScreenDTO screen;
    private List<ReservedSeatDTO> reservedSeats;
}
