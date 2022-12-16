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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("이름: ").append(name).append("\n")
                .append("생일: ").append(birth).append("\n")
                .append("출생지: ").append(birthPlace).append("\n")
                .append("출연 작품 목록:").append("\n");

        for(Filmography filmography : filmographyList) {
            sb.append(filmography).append("\n");
        }
        return sb.toString();
    }
}
