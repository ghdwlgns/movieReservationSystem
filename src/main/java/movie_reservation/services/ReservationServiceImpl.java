package movie_reservation.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.daos.*;
import movie_reservation.data.ReservationDTO;
import movie_reservation.data.ScreenDTO;
import movie_reservation.entities.*;
import movie_reservation.query.BooleanExpressions;
import movie_reservation.query.QueryIngredients;
import movie_reservation.response_dtos.ReservationResponse;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.State;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {
    ReservationDAO reservationDAO;
    ScreenDAO screenDAO;
    UserDAO userDAO;
    ReservedSeatDAO reservedSeatDAO;
    SeatDAO seatDAO;

    EntityManager entityManager;
    EntityTransaction entityTransaction;
    JPAQueryFactory queryFactory;
    BooleanExpressions booleanExpressions;

    public ReservationServiceImpl() {
        entityManager = QueryIngredients.getInstance().getEntityManager();
        entityTransaction = entityManager.getTransaction();
        queryFactory = new JPAQueryFactory(entityManager);

        reservationDAO = new ReservationDAOImpl(entityManager);
        screenDAO = new ScreenDAOImpl(entityManager);
        userDAO = new UserDAOImpl(entityManager);
        reservedSeatDAO = new ReservedSeatDAOImpl(entityManager);
        seatDAO = new SeatDAOImpl(entityManager);

        booleanExpressions = new BooleanExpressions();
    }

    @Override
    public void makeReservation(ScreenDTO screen, String userName, List<SeatNumber> seatNumbers) {
        // Controller에서 얘 호출하면 Seat 상태 unavailable로 바꾸는 거 필요
        try {
            entityTransaction.begin();

            Screen screenFind = screenDAO.findScreenByMovieTitleAndStartTime(screen.getMovieTitle(), screen.getStartTime()).get(0); // 얘는 무조건 하나임
            User user = userDAO.findUser(userName);
            Theater theater = screenFind.getTheater();
            Reservation reservation = new Reservation(user, screenFind);
            reservationDAO.addReservation(reservation);

            convertToReservedSeats(seatNumbers, screenFind, theater, reservation);

            reservationDAO.addReservation(reservation);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public List<ReservationResponse> findReservationsByUser(String userName) {
        return reservationDAO.findReservationsByUser(userName).stream()
                .map(Reservation::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> findReservationsByState(String userName, State state) {
        return reservationDAO.findReservationsByState(userName, state).stream()
                .map(Reservation::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelReservation(ReservationDTO reservation) {
        try {
            entityTransaction.begin();

            reservationDAO.cancelReservation(reservation.getUserName(), reservation.getScreen().getMovieTitle(),
                    reservation.getScreen().getStartTime());

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void expireReservation(ReservationDTO reservation) {
        try {
            entityTransaction.begin();

            reservationDAO.expireReservation(reservation.getUserName(), reservation.getScreen().getMovieTitle(),
                    reservation.getScreen().getEndTime());

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void emClose() {
        entityManager.close();
    }

    private void convertToReservedSeats(List<SeatNumber> seatNumbers, Screen screenFind, Theater theater, Reservation reservation) {
        for (SeatNumber seatNumber : seatNumbers) {
            Seat seat = seatDAO.findSeatByTheaterAndSeatNumber(theater, seatNumber);
            ReservedSeat reservedSeat = new ReservedSeat(seat, screenFind, reservation);
            reservedSeatDAO.addReservedSeat(reservedSeat);
        }
    }
}