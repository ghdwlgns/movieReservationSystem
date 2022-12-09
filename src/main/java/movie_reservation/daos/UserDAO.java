package movie_reservation.daos;

import movie_reservation.entities.User;
import movie_reservation.types.Address;

import java.util.List;

public interface UserDAO {
    void addUser(User user);    // saveUser
    User findUser(String userName);
    List<User> findAllUsers();
    void updateUserAge(String userName, Long ageBefore, Long ageAfter);
    void updateUserAddress(String userName, Address addressBefore, Address addressAfter);
    void removeUser(String userName);
    void removeAllUsers();
}