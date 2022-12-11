package movie_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
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

    public Reservation(User user, Screen screen, List<ReservedSeat> reservedSeats) {
        setUser(user);
        setScreen(screen);
        state = State.RESERVED;
        this.reservedSeats = reservedSeats;
    }

    private void setScreen(Screen screen) {
        this.screen = screen;
        this.screen.reserveScreen(this);
    }

    private void setUser(User user) {
        this.user = user;

        if(!this.user.getReservationList().contains(this))
            this.user.getReservationList().add(this);
    }
}
