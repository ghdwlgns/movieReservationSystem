package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.*;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import javax.persistence.EntityManager;
import java.util.List;

public class SeatDAOImpl implements SeatDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QSeat qSeat;
    private QReservedSeat qReservedSeat;

    public SeatDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qSeat = new QSeat("seat");
        qReservedSeat = new QReservedSeat("reservedSeat");
    }

    @Override
    public void addSeat(Seat seat) {
        entityManager.persist(seat);
    }

    @Override
    public Seat findSeatByTheaterAndSeatNumber(Theater theater, SeatNumber seatNumber) {
        return queryFactory.selectFrom(qSeat)
                .where(qSeat.theater.eq(theater), qSeat.seatNumber.eq(seatNumber))
                .fetchFirst();
    }

    @Override
    public List<Seat> findSeatsByScreen(Screen screen) {
        // 해당 상영의 예약 안된 자리만 보여줌
        return queryFactory.select(qSeat)
                .from()
                .where(qSeat.theater.screens.contains(screen),
                        qSeat.seatNumber.ne(qReservedSeat.seat.seatNumber),
                        qReservedSeat.screen.eq(screen))
                .orderBy(qSeat.seatNumber.col.asc(), qSeat.seatNumber.row.asc())
                .fetch();
    }

    @Override
    public List<Seat> findSeats() {
        return queryFactory.selectFrom(qSeat)
                .orderBy(qSeat.seatNumber.col.asc(), qSeat.seatNumber.row.asc())
                .fetch();
    }

    @Override
    public void updateSeats(SeatNumber seatNumber, SeatState seatState) {
        List<Seat> seats = queryFactory.selectFrom(qSeat)
                .where(qSeat.seatNumber.eq(seatNumber))
                .fetch();

        for(Seat seat : seats) {
            seat.setState(seatState);
        }

        entityManager.flush();
    }

    @Override
    public void removeSeat(Theater theater, SeatNumber seatNumber) {
        Seat seatFind = queryFactory.selectFrom(qSeat)
                .where(qSeat.theater.eq(theater), qSeat.seatNumber.eq(seatNumber))
                .fetchFirst();
        entityManager.remove(seatFind);

        entityManager.flush();
    }

    @Override
    public void removeAllSeats() {
        List<Seat> seats = queryFactory.selectFrom(qSeat)
                .fetch();

        for(Seat seat : seats)
            entityManager.remove(seat);

        entityManager.flush();
    }
}
