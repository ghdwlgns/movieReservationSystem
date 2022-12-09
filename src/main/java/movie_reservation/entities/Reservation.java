package movie_reservation.entities;

import movie_reservation.types.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "reservation")
    private List<ReservedSeat> reservedSeats;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCREEN_ID")
    private Screen screen;

    public Reservation() {

    }

    public Reservation(User user, Screen screen) {
        this.user = user;
        this.screen = screen;
        state = State.RESERVED;
        reservedSeats = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<ReservedSeat> getReservedSeats() {
        return this.reservedSeats;
    }

    public User getUser() {
        return user;
    }

    public State getState() {
        return state;
    }

    public Screen getScreen() {
        return screen;
    }
}
