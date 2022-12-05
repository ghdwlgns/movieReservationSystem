package movie_reservation.entities;

import movie_reservation.types.Casting;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
public class ActorList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTOR_LIST_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACTOR_ID")
    private Actor actor;
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Casting castingAs;

    public ActorList() {

    }

    public ActorList(Casting castingAs) {
        this.castingAs = castingAs;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;

        if(!this.movie.getActorLists().contains(this))
            this.movie.getActorLists().add(this);
    }

    public void setActor(Actor actor) {
        this.actor = actor;

        if(!this.actor.getActorLists().contains(this))
            this.actor.getActorLists().add(this);
    }
}
