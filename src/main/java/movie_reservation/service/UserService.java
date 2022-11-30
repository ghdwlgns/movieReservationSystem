package movie_reservation.service;

import movie_reservation.entities.Reservation;
import movie_reservation.entities.User;

public interface UserService {
    void addUser(User user);
    void findUser();
    void findUsers();
    void updateUser();
    void removeUser();
    Reservation findReservationByUserName(String userName);
}