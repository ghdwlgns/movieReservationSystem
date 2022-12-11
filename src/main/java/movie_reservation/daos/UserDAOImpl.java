package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.QUser;
import movie_reservation.entities.User;
import movie_reservation.types.Address;

import javax.persistence.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QUser qUser;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qUser = new QUser("user");
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUser(String userName) {
        return queryFactory.selectFrom(qUser)
                .where(qUser.name.eq(userName))
                .fetchFirst();
    }

    @Override
    public List<User> findAllUsers() {
        return queryFactory.selectFrom(qUser)
                .orderBy(qUser.name.asc())
                .fetch();
    }

    @Override
    public void updateUserAge(String userName, Long ageAfter) {
        User userFind = queryFactory.selectFrom(qUser)
                .where(qUser.name.eq(userName))
                .fetchFirst();

        userFind.changeAge(ageAfter);

        entityManager.flush();
    }

    @Override
    public void updateUserAddress(String userName, Address addressAfter) {
        User userFind = queryFactory.selectFrom(qUser)
                .where(qUser.name.eq(userName))
                .fetchFirst();

        userFind.changeAddress(addressAfter);

        entityManager.flush();
    }

    @Override
    public void removeUser(String userName) {
        User userFind = queryFactory.selectFrom(qUser)
                .where(qUser.name.eq(userName))
                .fetchOne();

        entityManager.remove(userFind);
    }

    @Override
    public void removeAllUsers() {

        List<User> users = queryFactory.selectFrom(qUser)
                .fetch();

        for(User user : users) {
            entityManager.remove(user);
        }
    }
}
