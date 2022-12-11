package movie_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class ReservedSeat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVED_SEAT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID", nullable = false)
    private Seat seat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCREEN_ID", nullable = false)
    private Screen screen;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "RESERVATION_ID", nullable = false)
    private Reservation reservation;

    public ReservedSeat(Seat seat, Screen screen, Reservation reservation) {
        setSeat(seat);
        setScreen(screen);
        setReservation(reservation);
    }

    private void setScreen(Screen screen) {
        this.screen = screen;

        if(!this.screen.getReservedSeats().contains(this))
            this.screen.getReservedSeats().add(this);
    }

    private void setSeat(Seat seat) {
        this.seat = seat;

        if(!this.seat.getReservedSeats().contains(this))
            this.seat.getReservedSeats().add(this);
    }

    private void setReservation(Reservation reservation) {
        this.reservation = reservation;
        if(!this.reservation.getReservedSeats().contains(this))
            this.reservation.getReservedSeats().add(this);
    }
}
