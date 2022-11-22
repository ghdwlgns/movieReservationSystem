package movie_reservation.entities;

import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import javax.persistence.*;
import java.util.LinkedList;
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
    @Column(nullable = false, updatable = false)
    private SeatNumber seatNumber;
    @Enumerated(EnumType.STRING)
    @Column(updatable = true, nullable = false)
    private SeatState state;

    @OneToMany(mappedBy = "seat")
    private List<ReservedSeat> reservedSeats = new LinkedList<>();

    public Seat() {

    }

    public Seat(Theater theater, SeatNumber seatNumber, SeatState state) {
        setTheater(theater);
        this.seatNumber = seatNumber;
        this.state = state;
    }

    public List<ReservedSeat> getReservedSeats() {
        return this.reservedSeats;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
        if(!this.theater.getSeats().contains(this))
            this.theater.getSeats().add(this);
    }
}
