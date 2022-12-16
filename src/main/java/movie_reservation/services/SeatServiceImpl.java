package movie_reservation.services;

import movie_reservation.daos.*;
import movie_reservation.data.SeatDTO;
import movie_reservation.entities.Screen;
import movie_reservation.entities.Seat;
import movie_reservation.entities.Theater;
import movie_reservation.query.QueryIngredients;
import movie_reservation.request_dtos.ScreenRequest;
import movie_reservation.response_dtos.SeatResponse;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;

public class SeatServiceImpl implements SeatService {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    private SeatDAO seatDAO;
    private ScreenDAO screenDAO;
    private TheaterDAO theaterDAO;

    public SeatServiceImpl() {
        entityManager = QueryIngredients.getInstance().getEntityManager();
        entityTransaction = entityManager.getTransaction();

        seatDAO = new SeatDAOImpl(entityManager);
        screenDAO = new ScreenDAOImpl(entityManager);
        theaterDAO = new TheaterDAOImpl(entityManager);
    }

    @Override
    public void registerSeat(SeatDTO seatDTO) {
        try {
            entityTransaction.begin();

            Theater theater = theaterDAO.findTheater(seatDTO.getTheater().getFloor(), seatDTO.getTheater().getTheaterName());
            seatDAO.addSeat(new Seat(theater, seatDTO.getSeatNumber()));

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }

    }

    @Override
    public List<SeatResponse> findSeatsByScreen(ScreenRequest screenRequest) {
        Screen screen = screenDAO.findScreenByMovieTitleAndStartTime(screenRequest.getMovieTitle(), screenRequest.getStarTime()).get(0);
        return seatDAO.findSeatsByScreen(screen).stream()
                .map(Seat::toResponse)
                .toList();
    }

    @Override
    public List<SeatResponse> findAllSeats() {
        return seatDAO.findSeats().stream()
                .map(Seat::toResponse)
                .toList();
    }

    @Override
    public void updateSeatState(List<SeatNumber> seatNumberList, SeatState seatState) {
        try {
            entityTransaction.begin();

            for(SeatNumber seatNumber : seatNumberList) {
                seatDAO.updateSeats(seatNumber, seatState);
            }

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeAllSeats() {
        seatDAO.removeAllSeats();
    }

    @Override
    public void emClose() {
        entityManager.close();
    }
}
