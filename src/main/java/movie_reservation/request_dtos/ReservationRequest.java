package movie_reservation.request_dtos;

import movie_reservation.data.ScreenDTO;
import movie_reservation.types.State;

public class ReservationRequest {
    private String userName;
    private State state;
    private ScreenDTO screen;
}
