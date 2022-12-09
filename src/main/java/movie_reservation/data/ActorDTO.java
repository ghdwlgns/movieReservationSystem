package movie_reservation.data;

import movie_reservation.entities.Actor;
import movie_reservation.entities.ActorRole;
import movie_reservation.types.Filmography;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActorDTO {
    private String name;
    private LocalDate birth;
    private List<Filmography> filmographyList;
    private BigDecimal height;
    private String instagramId;
    private List<ActorRoleDTO> actorRoles;

    public ActorDTO(Actor actor) {
        name = actor.getName();
        birth = actor.getBirth();
        filmographyList = actor.getFilmographyList();
        height = actor.getHeight();
        instagramId = actor.getInstagramId();
        init(actor.getActorLists());
    }

    private void init(List<ActorRole> actorRoles) {
        this.actorRoles = new ArrayList<>();

        for(ActorRole i : actorRoles) {
            ActorRoleDTO actorRole = new ActorRoleDTO(i);
            this.actorRoles.add(actorRole);
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public List<Filmography> getFilmographyList() {
        return filmographyList;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public String getInstagramId() {
        return instagramId;
    }

    public List<ActorRoleDTO> getActorRoles() {
        return actorRoles;
    }
}
