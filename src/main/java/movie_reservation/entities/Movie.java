package movie_reservation.entities;

import movie_reservation.types.Genre;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Movie extends UploadedTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private Long id;

    private String title;
    private String releaseDate;
    @OneToMany(mappedBy = "movie")
    private List<ActorList> actorLists = new LinkedList<>();
    @ManyToOne
    @JoinColumn(name = "DIRECTOR_ID")
    private Director director;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(nullable = false, updatable = false)
    private Long runningTime;
    @OneToMany(mappedBy = "movie")
    private List<Screen> screens = new LinkedList<>();

    public Movie() {

    }

    public Movie(String title, String releaseDate, Genre genre, Long runningTime) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.runningTime = runningTime;
        create();
    }

    public List<ActorList> getActorLists() {
        return this.actorLists;
    }

    public List<Screen> getScreens() {
        return this.screens;
    }

    public Long getRunningTime() {
        return this.runningTime;
    }
}
