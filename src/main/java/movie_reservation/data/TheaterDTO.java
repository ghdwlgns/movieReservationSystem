package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.Screen;
import movie_reservation.entities.Seat;
import movie_reservation.entities.Theater;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TheaterDTO {
    private String theaterName;
    private String floor;
    private List<ScreenDTO> screens;
    private List<SeatDTO> seats;
}
