package movie_reservation.request_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScreenRequest {
    private String movieTitle;
    private LocalTime starTime;
}
