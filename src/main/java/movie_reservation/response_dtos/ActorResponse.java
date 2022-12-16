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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("이름: ").append(name).append("\n")
                .append("생일: ").append(birth).append("\n")
                .append("키: ").append(height.toString()).append("\n")
                .append("인스타그램: ").append(instagramId).append("\n")
                .append("출연 작품 목록:").append("\n");

        for(Filmography filmography : filmographyList) {
            sb.append(filmography).append("\n");
        }

        return sb.toString();
    }
}
