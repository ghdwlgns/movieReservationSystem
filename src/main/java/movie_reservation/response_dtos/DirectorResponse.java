package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Filmography;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DirectorResponse {
    private String name;
    private String birth;
    private String birthPlace;
    private List<Filmography> filmographyList;
}
