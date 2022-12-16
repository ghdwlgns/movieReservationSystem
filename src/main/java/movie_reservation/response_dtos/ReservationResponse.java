package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.data.ScreenDTO;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.State;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationResponse {
    private String userName;
    private State state;
    private ScreenDTO screen;
    private List<SeatNumber> reservedSeats;

    @Override
    public String toString() {
        return "사용자 이름: " + userName + "\n"
                + "예약 상태: " + state + "\n"
                + "영화 제목: " + screen.getMovieTitle() + "\n"
                + "상영관: " + screen.getTheaterFloor() + " " + screen.getTheaterName() + "\n"
                + "시작 시간: " + screen.getStartTime() + "\n"
                + "종료 시간: " + screen.getEndTime();
    }
}
