package movie_reservation.response_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie_reservation.types.Address;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponse {
    private String userName;
    private Long age;
    private Address address;
}
