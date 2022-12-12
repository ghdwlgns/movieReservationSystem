package movie_reservation.entities;

import movie_reservation.response_dtos.ActorResponse;
import movie_reservation.types.Filmography;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ACTOR")
public class Actor extends MovieStar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTOR_ID", updatable = false)
    private Long id;

    private BigDecimal height;
    @Column(unique = true)
    private String instagramId;
    @OneToMany(mappedBy = "actor")
    private List<ActorRole> actorRoles;

    protected Actor() {
        super();
    }

    public Actor(String name, LocalDate birth, BigDecimal height, String instagramId) {
        super(name, birth);
        this.height = height;
        this.instagramId = instagramId;
        actorRoles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return super.getName();
    }

    public LocalDate getBirth() {
        return super.getBirth();
    }

    public List<Filmography> getFilmographyList() {
        return super.getFilmographyList();
    }

    public List<ActorRole> getActorLists() {
        return this.actorRoles;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public String getInstagramId() {
        return instagramId;
    }

    public ActorResponse toResponse() {
        return new ActorResponse(getName(), getBirth().toString(), height, instagramId, getFilmographyList());
    }
}
