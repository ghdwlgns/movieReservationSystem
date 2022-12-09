package movie_reservation.entities;

import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEAT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;
    @Embedded
    @Column(name = "SEAT_NUMBER", nullable = false, updatable = false)
    private SeatNumber seatNumber;
    @Enumerated(EnumType.STRING)
    @Column(updatable = true, nullable = false)
    private SeatState state;    // 가용 / 불가용

    @OneToMany(mappedBy = "seat")
    private List<ReservedSeat> reservedSeats;

    public Seat() {

    }

    public Seat(Theater theater, SeatNumber seatNumber) {
        setTheater(theater);
        this.seatNumber = seatNumber;
        state = SeatState.AVAILABLE;
        reservedSeats = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<ReservedSeat> getReservedSeats() {
        return this.reservedSeats;
    }

    public SeatState getState() {
        return state;
    }

    public Theater getTheater() {
        return theater;
    }

    public SeatNumber getSeatNumber() {
        return seatNumber;
    }

    private void setTheater(Theater theater) {
        this.theater = theater;
        if(!this.theater.getSeats().contains(this))
            this.theater.getSeats().add(this);
    }

    public void setState(SeatState state) {
        this.state = state;
    }
}
