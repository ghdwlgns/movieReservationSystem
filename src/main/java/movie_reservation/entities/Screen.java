package movie_reservation.entities;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Screen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCREEN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;

    @OneToMany(mappedBy = "screen")
    private List<ReservedSeat> reservedSeats = new LinkedList<>();
    @OneToOne(mappedBy = "reservation")
    private Reservation reservation;

    public Screen() {

    }

    public Screen(Movie movie, Theater theater, LocalTime startTime) {
        setMovie(movie);
        setTheater(theater);
        this.startTime = startTime;
        calculateEndTime();
    }

    public void setMovie(Movie movie) {
        if(this.movie != null)
            this.movie = movie;
        this.movie.getScreens().add(this);
    }

    public void setTheater(Theater theater) {
        if(this.theater != null)
            this.theater = theater;
        this.theater.getScreens().add(this);
    }

    public List<ReservedSeat> getReservedSeats() {
        return this.reservedSeats;
    }

    private void calculateEndTime() {
        this.endTime = startTime.plusMinutes(movie.getRunningTime());
    }
}
