package movie_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.response_dtos.MovieResponse;
import movie_reservation.types.Casting;
import movie_reservation.types.Filmography;
import movie_reservation.types.Genre;
import movie_reservation.types.Job;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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

    public Movie(String title, String releaseDate, Genre genre, Long runningTime, Director director, LocalDateTime dateCreated, LocalDateTime lastModified) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.runningTime = runningTime;
        setDirector(director);
        create(dateCreated);
        modify(lastModified);

        actorRoles = new ArrayList<>();
        screens = new ArrayList<>();
    }

    private void setDirector(Director director) {
        this.director = director;

        if(!director.getMoviesDirected().contains(this)) {
            director.getMoviesDirected().add(this);
            Filmography filmography = new Filmography(title, Job.DIRECTOR, Casting.DIRECTOR);
            director.getFilmographyList().add(filmography);
        }
    }

    public void changeReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MovieResponse toResponse() {
        return new MovieResponse(title, releaseDate, genre, runningTime);
    }
}
