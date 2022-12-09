package movie_reservation.data;

import movie_reservation.entities.ActorRole;
import movie_reservation.entities.Movie;
import movie_reservation.entities.Screen;
import movie_reservation.types.Genre;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
    private String title;
    private String releaseDate;
    private List<ActorRoleDTO> actorRoles;
    private DirectorDTO director;
    private Genre genre;
    private Long runningTime;
    private List<ScreenDTO> screens;

    public MovieDTO(Movie movie) {
        title = movie.getTitle();
        releaseDate = movie.getReleaseDate();
        genre = movie.getGenre();
        runningTime = movie.getRunningTime();
        initActorRoles(movie.getActorRoles());
        initScreens(movie.getScreens());
        director = new DirectorDTO(movie.getDirector());
    }

    private void initActorRoles(List<ActorRole> actorRoles) {
        this.actorRoles = new ArrayList<>();

        for(ActorRole i : actorRoles) {
            ActorRoleDTO actorRole = new ActorRoleDTO(i);
            this.actorRoles.add(actorRole);
        }
    }

    private void initScreens(List<Screen> screens) {
        this.screens = new ArrayList<>();

        for(Screen i : screens) {
            ScreenDTO screen = new ScreenDTO(i);
            this.screens.add(screen);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<ActorRoleDTO> getActorRoles() {
        return actorRoles;
    }

    public DirectorDTO getDirector() {
        return director;
    }

    public Genre getGenre() {
        return genre;
    }

    public Long getRunningTime() {
        return runningTime;
    }

    public List<ScreenDTO> getScreens() {
        return screens;
    }
}
