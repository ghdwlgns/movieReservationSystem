package movie_reservation.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Theater {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THEATER_ID")
    private Long id;

    @Column(updatable = false, nullable = false)
    private String theaterName;
    @Column(updatable = false, nullable = false)
    private String floor;
    @OneToMany(mappedBy = "theater")
    private List<Screen> screens = new LinkedList<>();
    @OneToMany(mappedBy = "theater")
    private List<Seat> seats = new LinkedList<>();

    public Theater() {

    }

    public Theater(String theaterName, String floor) {
        this.theaterName = theaterName;
        this.floor = floor;
    }

    public List<Screen> getScreens() {
        return this.screens;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }
}
