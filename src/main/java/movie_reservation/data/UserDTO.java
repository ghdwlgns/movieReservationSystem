package movie_reservation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Address;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {
    private String name;
    private Long age;
    private Address address;
    private List<ReservationDTO> reservations;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModified;
}
