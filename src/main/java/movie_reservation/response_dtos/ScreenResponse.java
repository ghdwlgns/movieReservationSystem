package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScreenResponse {
    private String movieTitle;
    private String floor;
    private String theaterName;
    private LocalTime startTime;
    private LocalTime endTime;
}
