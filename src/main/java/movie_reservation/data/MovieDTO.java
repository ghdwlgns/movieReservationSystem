package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.ActorRole;
import movie_reservation.entities.Movie;
import movie_reservation.entities.Screen;
import movie_reservation.types.Genre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieDTO {
    private String title;
    private String releaseDate;
    private List<ActorRoleDTO> actorRoles;
    private DirectorDTO director;
    private Genre genre;
    private Long runningTime;
    private List<ScreenDTO> screens;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
