package movie_reservation.services;

import movie_reservation.daos.UserDAO;
import movie_reservation.daos.UserDAOImpl;
import movie_reservation.data.UserDTO;
import movie_reservation.entities.User;
import movie_reservation.query.QueryIngredients;
import movie_reservation.request_dtos.UserRequest;
import movie_reservation.response_dtos.UserResponse;
import movie_reservation.types.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private QueryIngredients query;

    public UserServiceImpl() {
        query = new QueryIngredients();
        entityManager = query.getEntityManager();
        entityTransaction = entityManager.getTransaction();

        userDAO = new UserDAOImpl(entityManager);
    }

    @Override
    public void registerUser(UserDTO user) {
        try {
            entityTransaction.begin();

            userDAO.addUser(user.toUser());

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public UserResponse findUser(String userName) {
        return userDAO.findUser(userName).toResponse();
    }

    @Override
    public List<UserResponse> findAllUser() {
        return userDAO.findAllUsers().stream()
                .map(User::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void modifyUserAge(String userName, Long age) {
        try {
            entityTransaction.begin();

            userDAO.updateUserAge(userName, age);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void modifyUserAddress(String userName, Address address) {
        try {
            entityTransaction.begin();

            userDAO.updateUserAddress(userName, address);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeUser(UserRequest user) {
        try {
            entityTransaction.begin();

            userDAO.removeUser(user.getName());

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }
}
