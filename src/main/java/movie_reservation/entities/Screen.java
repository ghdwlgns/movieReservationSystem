package movie_reservation.entities;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Screen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCREEN_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;

    @OneToMany(mappedBy = "screen")
    private List<ReservedSeat> reservedSeats;
    @OneToOne(mappedBy = "reservation")
    private Reservation reservation;

    public Screen() {

    }

    public Screen(Movie movie, Theater theater, LocalTime startTime) {
        setMovie(movie);
        setTheater(theater);
        this.startTime = startTime;
        reservedSeats = new ArrayList<>();
        calculateEndTime();
    }

    private void setMovie(Movie movie) {
        if(this.movie != null)
            this.movie = movie;
        this.movie.getScreens().add(this);
    }

    private void setTheater(Theater theater) {
        if(this.theater != null)
            this.theater = theater;
        this.theater.getScreens().add(this);
    }

    public Long getId() {
        return id;
    }

    public List<ReservedSeat> getReservedSeats() {
        return this.reservedSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Reservation getReservation() {
        return reservation;
    }

    private void calculateEndTime() {
        this.endTime = startTime.plusMinutes(movie.getRunningTime());
    }
}
