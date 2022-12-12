package movie_reservation.services;

import movie_reservation.daos.ScreenDAO;
import movie_reservation.daos.ScreenDAOImpl;
import movie_reservation.daos.SeatDAO;
import movie_reservation.daos.SeatDAOImpl;
import movie_reservation.data.SeatDTO;
import movie_reservation.entities.Screen;
import movie_reservation.entities.Seat;
import movie_reservation.query.QueryIngredients;
import movie_reservation.request_dtos.ScreenRequest;
import movie_reservation.response_dtos.SeatResponse;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.util.List;

public class SeatServiceImpl implements SeatService {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private QueryIngredients query;

    private SeatDAO seatDAO;
    private ScreenDAO screenDAO;

    public SeatServiceImpl() {
        query = new QueryIngredients();
        entityManager = query.getEntityManager();
        entityTransaction = entityManager.getTransaction();

        seatDAO = new SeatDAOImpl(entityManager);
        screenDAO = new ScreenDAOImpl(entityManager);
    }

    @Override
    public void registerSeats(List<SeatDTO> seatDTOList) {
        try {
            entityTransaction.begin();

            List<Seat> seatList = seatDTOList.stream().map(SeatDTO::toEntity).toList();

            for(Seat seat : seatList) {
                seatDAO.addSeat(seat);
            }

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }

    }

    @Override
    public List<SeatResponse> findSeatsByScreen(ScreenRequest screenRequest) {
        Screen screen = screenDAO.findScreenByMovieTitleAndStartTime(screenRequest.getMovieTitle(), screenRequest.getStarTime());
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

    }
}
