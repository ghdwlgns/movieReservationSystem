package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.QReservation;
import movie_reservation.entities.Reservation;
import movie_reservation.types.State;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.List;

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

    @Override
    public List<Reservation> findReservationsByUser(String userName) {
        return queryFactory.selectFrom(qReservation)
                .where(qReservation.user.name.eq(userName))
                .fetch();
    }

    @Override
    public List<Reservation> findReservationsByState(String userName, State state) {
        return queryFactory.selectFrom(qReservation)
                .where(qReservation.user.name.eq(userName),
                        qReservation.state.eq(state))
                .fetch();
    }

    @Override
    public Reservation findReservation(String userName, String movieTitle, LocalTime startTime) {
        return queryFactory.selectFrom(qReservation)
                .where(qReservation.user.name.eq(userName),
                        qReservation.screen.movie.title.eq(movieTitle),
                        qReservation.screen.startTime.eq(startTime))
                .fetchFirst();
    }

    @Override
    public void cancelReservation(String userName, String movieTitle, LocalTime startTime) {
        Reservation reservationToCancel = queryFactory.selectFrom(qReservation)
                .where(qReservation.user.name.eq(userName),
                        qReservation.screen.movie.title.eq(movieTitle),
                        qReservation.screen.startTime.eq(startTime))
                .fetchFirst();

        if(reservationToCancel.getState() == State.RESERVED)
            reservationToCancel.cancelThis();
    }

    @Override
    public void expireReservation(String userName, String movieTitle, LocalTime endTime) {
        Reservation reservationToExpire = queryFactory.selectFrom(qReservation)
                .where(qReservation.user.name.eq(userName),
                        qReservation.screen.movie.title.eq(movieTitle),
                        qReservation.screen.endTime.eq(endTime))
                .fetchFirst();

        if(endTime.isBefore(LocalTime.now()))
            reservationToExpire.expireThis();
    }


}
