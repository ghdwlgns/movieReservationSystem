package movie_reservation.entities;

import movie_reservation.types.Address;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User extends UploadedTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String name;
    private Long age;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList = new LinkedList<>();

    public User() {

    }

    public User(String name, Long age, String city, String street, String zipCode) {
        this.name = name;
        this.age = age;
        address = new Address(city, street, zipCode);
        create();
    }

    public List<Reservation> getReservationList() {
        return this.reservationList;
    }
}
