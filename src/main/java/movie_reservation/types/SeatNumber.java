package movie_reservation.types;

import javax.persistence.Embeddable;

@Embeddable
public class SeatNumber {
    private Character row;
    private Long col;

    public SeatNumber() {

    }

    public SeatNumber(Character row, Long col) {
        this.row = row;
        this.col = col;
    }
}
