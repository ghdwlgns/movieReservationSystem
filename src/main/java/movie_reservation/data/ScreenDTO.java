package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.ReservedSeat;
import movie_reservation.entities.Screen;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScreenDTO {
    private String movieTitle;
    private String theaterName;
    private String theaterFloor;
    private LocalTime startTime;
    private LocalTime endTime;
}
