package movie_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.response_dtos.TheaterResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Theater {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THEATER_ID")
    private Long id;

    @Column(updatable = false, nullable = false)
    private String theaterName;
    @Column(updatable = false, nullable = false)
    private String floor;
    @OneToMany(mappedBy = "theater")
    private List<Screen> screens;
    @OneToMany(mappedBy = "theater")
    private List<Seat> seats;

    public Theater(String theaterName, String floor) {
        this.theaterName = theaterName;
        this.floor = floor;
        this.screens = new ArrayList<>();
        this.seats = new ArrayList<>();
    }

    public TheaterResponse toResponse() {
        return new TheaterResponse(theaterName, floor);
    }
}
