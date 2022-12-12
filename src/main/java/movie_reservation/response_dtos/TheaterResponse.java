package movie_reservation.response_dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TheaterResponse {
    private String theaterName;
    private String floor;
}
