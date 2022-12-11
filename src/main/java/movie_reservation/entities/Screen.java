package movie_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    public Screen(Movie movie, Theater theater, LocalTime startTime, LocalTime endTime) {
        setMovie(movie);
        setTheater(theater);
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservedSeats = new ArrayList<>();
    }

    private void setMovie(Movie movie) {
        this.movie = movie;

        if(!this.movie.getScreens().contains(this))
            this.movie.getScreens().add(this);
    }

    private void setTheater(Theater theater) {
        this.theater = theater;

        if(!this.theater.getScreens().contains(this))
            this.theater.getScreens().add(this);
    }

    public void reserveScreen(Reservation reservation) {
        this.reservation = reservation;
    }
}
