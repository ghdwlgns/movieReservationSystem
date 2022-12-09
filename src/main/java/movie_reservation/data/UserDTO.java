package movie_reservation.data;

import movie_reservation.entities.Reservation;
import movie_reservation.entities.User;
import movie_reservation.types.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String name;
    private Long age;
    private Address address;
    private List<ReservationDTO> reservations;
    private LocalDateTime lastModified;

    public UserDTO(User user) {
        name = user.getName();
        age = user.getAge();
        address = user.getAddress();
        init(user.getReservationList());
    }

    private void init(List<Reservation> reservations) {
        this.reservations = new ArrayList<>();

        for(Reservation i : reservations) {
            ReservationDTO reservation = new ReservationDTO(i);
            this.reservations.add(reservation);
        }
    }

    public void setLastModified() {
        this.lastModified = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Long getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }
}
