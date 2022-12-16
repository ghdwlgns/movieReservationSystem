package movie_reservation.entities;

import movie_reservation.types.Casting;
import movie_reservation.types.Filmography;
import movie_reservation.types.Job;

import javax.persistence.*;

@Entity
public class ActorRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTOR_ROLE_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ACTOR_ID")
    private Actor actor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Casting castingAs;

    public ActorRole() {

    }

    public ActorRole(Movie movie, Actor actor, Casting castingAs) {
        setMovie(movie);
        setActor(actor);
        this.castingAs = castingAs;
        addNewFilmography();
    }

    private void addNewFilmography() {
        Filmography filmography = new Filmography(movie.getTitle(), Job.ACTOR, castingAs);

        if(!actor.getFilmographyList().contains(filmography))
            actor.getFilmographyList().add(filmography);
    }

    private void setMovie(Movie movie) {
        this.movie = movie;

        if(!this.movie.getActorRoles().contains(this))
            this.movie.getActorRoles().add(this);
    }

    private void setActor(Actor actor) {
        this.actor = actor;

        if(!this.actor.getActorLists().contains(this))
            this.actor.getActorLists().add(this);
    }

    public Long getId() {
        return id;
    }

    public Actor getActor() {
        return actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public Casting getCastingAs() {
        return castingAs;
    }
}
