package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.Theater;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TheaterDTO {
    private String theaterName;
    private String floor;

    public Theater toEntity() {
        return new Theater(theaterName, floor);
    }
}
