package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Genre;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieDTO {
    private String title;
    private String releaseDate;
    private List<ActorRoleDTO> actorRoles;
    private String directorName;
    private Genre genre;
    private Long runningTime;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
