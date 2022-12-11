package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Genre;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieResponse {
    private String movieTitle;
    private String releaseDate;
    private Genre genre;
    private Long runningTime;

    public String toString() {
        return "영화 제목: " + movieTitle + "\n"
                + "출시일: " + releaseDate + "\n"
                + "장르: " + genre + "\n"
                + "상영 시간: " + runningTime;
    }
}
