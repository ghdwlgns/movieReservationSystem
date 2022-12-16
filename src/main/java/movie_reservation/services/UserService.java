package movie_reservation.services;

import movie_reservation.data.UserDTO;
import movie_reservation.request_dtos.UserRequest;
import movie_reservation.response_dtos.UserResponse;
import movie_reservation.types.Address;

import java.util.List;

public interface UserService {
    /**
     * 사용자 등록
     * 사용자 수정(age | address)
     */
    void registerUser(UserDTO user);
    UserResponse findUser(String userName);
    List<UserResponse> findAllUser();
    void modifyUserAge(String userName, Long age);
    void modifyUserAddress(String userName, Address address);
    void removeUser(UserRequest user);
    void emClose();
}
