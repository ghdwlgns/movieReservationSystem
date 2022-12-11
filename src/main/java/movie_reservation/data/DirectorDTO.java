package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Filmography;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DirectorDTO {
    private String name;
    private String birth;
    private List<Filmography> filmographyList;
    private String birthPlace;
    private List<MovieDTO> moviesDirected;
}
