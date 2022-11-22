package movie_reservation.entities;

import movie_reservation.types.Filmography;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@DiscriminatorValue("ACTOR")
public class Actor extends MovieStar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTOR_ID", updatable = false)
    private Long id;

    private BigDecimal height;
    @Column(unique = true, nullable = true)
    private String instagramId;
    @OneToMany(mappedBy = "actor")
    private List<ActorList> actorLists = new LinkedList<>();

    public Actor() {

    }

    public Actor(String name, Date birth, Filmography filmography, BigDecimal height, String instagramId) {
        this.setName(name);
        this.setBirth(birth);
        this.setFilmography(filmography);
        this.height = height;
        this.instagramId = instagramId;
    }

    public List<ActorList> getActorLists() {
        return this.actorLists;
    }
}
