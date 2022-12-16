package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.data.ScreenDTO;
import movie_reservation.request_dtos.ScreenRequest;

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

    @Override
    public String toString() {
        return "상영관: " + floor + " " + theaterName + "\n" +
                "시작 시간: " + startTime.toString() + "\n" +
                "종료 시간: " + endTime.toString();
    }

    public ScreenRequest toRequest() {
        return new ScreenRequest(movieTitle, startTime);
    }

    public ScreenDTO toDTO() {
        return new ScreenDTO(movieTitle, floor, theaterName, startTime, endTime);
    }
}
