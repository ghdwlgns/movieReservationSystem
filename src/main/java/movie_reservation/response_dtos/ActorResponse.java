package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Filmography;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ActorResponse {
    private String name;
    private String birth;
    private BigDecimal height;
    private String instagramId;
    private List<Filmography> filmographyList;
}
