package movie_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.response_dtos.ReservationResponse;
import movie_reservation.types.SeatNumber;
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

    public Reservation(User user, Screen screen) {
        setUser(user);
        setScreen(screen);
        state = State.RESERVED;
        this.reservedSeats = new ArrayList<>();
    }

    private void setScreen(Screen screen) {
        this.screen = screen;
    }

    private void setUser(User user) {
        this.user = user;

        if(!this.user.getReservationList().contains(this))
            this.user.getReservationList().add(this);
    }

    public void cancelThis() {
        state = State.CANCELED;
    }

    public void expireThis() {
        state = State.EXPIRED;
    }

    public ReservationResponse toResponse() {
        List<SeatNumber> reservedSeatList = new ArrayList<>();
        for(ReservedSeat reservedSeat : reservedSeats)
            reservedSeatList.add(reservedSeat.getSeat().getSeatNumber());

        return new ReservationResponse(user.getName(), state, screen.toScreenDTO(), reservedSeatList);
    }
}
