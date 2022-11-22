package movie_reservation.entities;

import movie_reservation.types.State;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "reservation")
    private List<ReservedSeat> reservedSeats = new LinkedList<>();

    @OneToOne
    @JoinColumn(name = "SCREEN_ID")
    private Screen screen;

    public Reservation() {

    }

    public Reservation(State state) {
        this.state = state;
    }

    public List<ReservedSeat> getReservedSeats() {
        return this.reservedSeats;
    }
}
