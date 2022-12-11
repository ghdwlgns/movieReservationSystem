package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Filmography;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActorDTO {
    private String name;
    private String birth;
    private List<Filmography> filmographyList;
    private BigDecimal height;
    private String instagramId;
    private List<ActorRoleDTO> actorRoles;
}
