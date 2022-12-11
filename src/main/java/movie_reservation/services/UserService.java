package movie_reservation.services;

import movie_reservation.data.UserDTO;

public interface UserService {
    /**
     * 사용자 등록
     * 사용자 수정(age | address)
     */
    void registerUser(UserDTO user);
    void modifyUserAge(UserDTO user, Long age);
    void modifyUserAddress(UserDTO user, String Address);
}
