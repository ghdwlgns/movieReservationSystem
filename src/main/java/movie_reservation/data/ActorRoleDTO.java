package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Casting;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActorRoleDTO {
    private String actorName;
    private String movieTitle;
    private Casting castingAs;
}
