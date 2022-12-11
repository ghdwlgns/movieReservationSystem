package movie_reservation.request_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Genre;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieRequest {
    private String movieTitle;
    private String releaseDate;
    private String director;
    private Genre genre;
    private Long runningTime;
}
