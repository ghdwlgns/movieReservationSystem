package movie_reservation.entities;

import javax.persistence.*;

@Entity
public class ReservedSeat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVED_SEAT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SEAT_ID")
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "SCREEN_ID")
    private Screen screen;
    @ManyToOne
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    public void setSeat(Seat seat) {
        this.seat = seat;
        if(!this.seat.getReservedSeats().contains(this))
            this.seat.getReservedSeats().add(this);
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
        if(!this.screen.getReservedSeats().contains(this))
            this.screen.getReservedSeats().add(this);
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        if(!this.reservation.getReservedSeats().contains(this))
            this.reservation.getReservedSeats().add(this);
    }
}
