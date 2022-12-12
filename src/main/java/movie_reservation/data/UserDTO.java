package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.entities.User;
import movie_reservation.types.Address;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {
    private String name;
    private Long age;
    private Address address;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModified;

    public User toUser() {
        return new User(name, age, address, dateCreated, lastModified);
    }
}
