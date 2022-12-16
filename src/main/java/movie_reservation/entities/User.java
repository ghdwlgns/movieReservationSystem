package movie_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.response_dtos.UserResponse;
import movie_reservation.types.Address;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User extends UploadedTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String name;
    private Long age;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList;

    public User(String name, Long age, Address address, LocalDateTime dateCreated, LocalDateTime lastModified) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.reservationList = new ArrayList<>();
        create(dateCreated);
        modify(lastModified);
    }

    public void changeAge(Long age) {
        this.age = age;
        modify(LocalDateTime.now());
    }

    public void changeAddress(Address address) {
        this.address = address;
        modify(LocalDateTime.now());
    }

    public UserResponse toResponse() {
        return new UserResponse(name, age, address);
    }
}
