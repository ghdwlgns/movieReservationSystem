package movie_reservation.types;

import javax.persistence.Embeddable;

@Embeddable
public class SeatNumber {
    private String hang;
    private String col;

    public SeatNumber() {

    }

    public SeatNumber(String hang, String col) {
        this.hang = hang;
        this.col = col;
    }

    @Override
    public String toString() {
        return hang + " " + col;
    }
}
