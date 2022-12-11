package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.QReservation;
import movie_reservation.entities.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReservationDAOImpl implements ReservationDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QReservation qReservation;

    public ReservationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qReservation = new QReservation("reservation");
    }

    @Override
    public void addReservation(Reservation reservation) {
        entityManager.persist(reservation);
    }
}
