package movie_reservation.entities;

import movie_reservation.types.Casting;
import movie_reservation.types.Filmography;
import movie_reservation.types.Genre;
import movie_reservation.types.Job;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie extends UploadedTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "RELEASE_DATE", nullable = false)
    private String releaseDate;
    @OneToMany(mappedBy = "movie")
    @Column(name = "ACTOR_ROLES", nullable = false)
    private List<ActorRole> actorRoles;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECTOR_ID", nullable = false)
    private Director director;
    @Column(name = "GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(nullable = false, updatable = false)
    private Long runningTime;
    @OneToMany(mappedBy = "movie")
    @Column(name = "SCREENS", nullable = false)
    private List<Screen> screens;

    public Movie() {

    }

    public Movie(String title, String releaseDate, Genre genre, Long runningTime) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.runningTime = runningTime;
        actorRoles = new ArrayList<>();
        screens = new ArrayList<>();
        create();
    }

    private void setDirector(Director director) {
        this.director = director;

        if(!director.getMoviesDirected().contains(this)) {
            director.getMoviesDirected().add(this);
            Filmography filmography = new Filmography(title, Job.DIRECTOR, Casting.DIRECTOR);
            director.getFilmographyList().add(filmography);
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<ActorRole> getActorRoles() {
        return actorRoles;
    }

    public Director getDirector() {
        return director;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<ActorRole> getActorLists() {
        return this.actorRoles;
    }

    public List<Screen> getScreens() {
        return this.screens;
    }

    public Long getRunningTime() {
        return this.runningTime;
    }

    public void changeReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
