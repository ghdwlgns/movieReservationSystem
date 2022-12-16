package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScreenDTO {
    private String movieTitle;
    private String theaterFloor;
    private String theaterName;
    private LocalTime startTime;
    private LocalTime endTime;
}
