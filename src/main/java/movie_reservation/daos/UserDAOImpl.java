package movie_reservation.daos;

import com.querydsl.core.NonUniqueResultException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.QUser;
import movie_reservation.entities.User;
import movie_reservation.types.Address;

import javax.persistence.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private JPAQueryFactory queryFactory;
    private QUser qUser;

    public UserDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("movie_reservation");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        queryFactory = new JPAQueryFactory(entityManager);
        qUser = new QUser("user");
    }

    public void terminateDAO() {
        entityManager.close();
        entityManagerFactory.close();
    }

    private void flushChange() {
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void addUser(User user) {
        try {
            entityTransaction.begin();

            entityManager.persist(user);
            flushChange();

            entityTransaction.commit();
        } catch(Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
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
    public void updateUserAge(String userName, Long ageBefore, Long ageAfter) {
        User userFind = null;
        Long userFindId = null;

        try {
            entityTransaction.begin();

            userFind = queryFactory.selectFrom(qUser)
                    .where(qUser.name.eq(userName),
                            qUser.age.eq(ageBefore))
                    .fetchFirst();

            userFindId = userFind.getId();

            User userUpdate = entityManager.find(User.class, userFindId);
            userUpdate.changeAge(ageAfter);

            flushChange();

            entityTransaction.commit();
        } catch(NullPointerException | NonUniqueResultException e) {
            e.printStackTrace();
        } catch(RollbackException e) {
            System.out.println("Error Message: " + e);
            entityTransaction.rollback();
        }
    }

    @Override
    public void updateUserAddress(String userName, Address addressBefore, Address addressAfter) {
        User userFind = null;
        Long userFindId = null;

        try {
            entityTransaction.begin();

            userFind = queryFactory.selectFrom(qUser)
                    .where(qUser.name.eq(userName),
                            qUser.address.eq(addressBefore))
                    .fetchFirst();

            userFindId = userFind.getId();

            User userUpdate = entityManager.find(User.class, userFindId);
            userUpdate.changeAddress(addressAfter);

            flushChange();

            entityTransaction.commit();
        } catch(NullPointerException | NonUniqueResultException e) {
            e.printStackTrace();
        } catch(RollbackException e) {
            System.out.println("Error Message: " + e);
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeUser(String userName) {
        User userFind = null;
        Long userFindId = null;

        try {
            entityTransaction.begin();

            userFind = queryFactory.selectFrom(qUser)
                    .where(qUser.name.eq(userName))
                    .fetchOne();
            userFindId = userFind.getId();

            User userRemove = entityManager.find(User.class, userFindId);
            entityManager.remove(userRemove);

            flushChange();

            entityTransaction.commit();
        } catch(NullPointerException e) {
            System.out.println("Error Message: " + e);
        } catch(RollbackException | IllegalStateException e) {
            System.out.println("Error Message: " + e);
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeAllUsers() {

        List<User> users = queryFactory.selectFrom(qUser)
                .fetch();

        try {
            entityTransaction.begin();

            for(User user : users)
                entityManager.remove(user);

            flushChange();
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }
}
