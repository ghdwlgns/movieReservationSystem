package movie_reservation.request_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Address;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRequest {
    private String name;
    private Long age;
    private Address address;
}
