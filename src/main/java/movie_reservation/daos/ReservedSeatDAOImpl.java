package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.QReservedSeat;
import movie_reservation.entities.ReservedSeat;

import javax.persistence.EntityManager;

public class ReservedSeatDAOImpl implements ReservedSeatDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QReservedSeat qReservedSeat;

    public ReservedSeatDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qReservedSeat = new QReservedSeat("reservedSeat");
    }

    @Override
    public void addReservedSeat(ReservedSeat reservedSeat) {
        entityManager.persist(reservedSeat);
    }
}
