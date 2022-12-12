package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.Actor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActorDTO {
    private String name;
    private String birth;
    private BigDecimal height;
    private String instagramId;

    public Actor toActor() {
        return new Actor(name, LocalDate.parse(birth), height, instagramId);
    }
}
