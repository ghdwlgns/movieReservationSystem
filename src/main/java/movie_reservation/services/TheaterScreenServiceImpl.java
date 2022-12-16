package movie_reservation.services;

import movie_reservation.daos.*;
import movie_reservation.data.ScreenDTO;
import movie_reservation.data.TheaterDTO;
import movie_reservation.entities.Movie;
import movie_reservation.entities.Screen;
import movie_reservation.entities.Theater;
import movie_reservation.query.QueryIngredients;
import movie_reservation.request_dtos.MovieRequest;
import movie_reservation.request_dtos.ScreenRequest;
import movie_reservation.response_dtos.ScreenResponse;
import movie_reservation.response_dtos.TheaterResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.time.LocalTime;
import java.util.List;

public class TheaterScreenServiceImpl implements TheaterScreenService {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    private TheaterDAO theaterDAO;
    private ScreenDAO screenDAO;
    private MovieDAO movieDAO;

    public TheaterScreenServiceImpl() {
        entityManager = QueryIngredients.getInstance().getEntityManager();
        entityTransaction = entityManager.getTransaction();

        theaterDAO = new TheaterDAOImpl(entityManager);
        screenDAO = new ScreenDAOImpl(entityManager);
        movieDAO = new MovieDAOImpl(entityManager);
    }

    @Override
    public void registerTheater(TheaterDTO theaterDTO) {
        try {
            entityTransaction.begin();

            theaterDAO.addTheater(theaterDTO.toEntity());

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void registerScreen(ScreenDTO screenDTO) {
        try {
            entityTransaction.begin();

            Movie movie = movieDAO.findMovie(screenDTO.getMovieTitle());
            Theater theater = theaterDAO.findTheater(screenDTO.getTheaterFloor(), screenDTO.getTheaterName());

            screenDAO.addScreen(new Screen(movie, theater, screenDTO.getStartTime(), screenDTO.getEndTime()));

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public List<TheaterResponse> findTheatersByFloor(String floor) {
        return theaterDAO.findTheatersByFloor(floor).stream()
                .map(Theater::toResponse)
                .toList();
    }

    @Override
    public List<ScreenResponse> findScreenByMovie(MovieRequest movie) {
        return screenDAO.findScreensByMovieTitle(movie.getMovieTitle()).stream()
                .map(Screen::toResponse)
                .toList();
    }

    @Override
    public List<ScreenResponse> findScreenBy(String movieTitle, LocalTime startTime) {
        return screenDAO.findScreenByMovieTitleAndStartTime(movieTitle, startTime).stream()
                .map(Screen::toResponse)
                .toList();
    }

    @Override
    public void removeAllTheater() {
        try {
            entityTransaction.begin();

            theaterDAO.removeAllTheaters();

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeScreen(ScreenRequest screenRequest) {
        try {
            entityTransaction.begin();

            screenDAO.removeScreen(screenRequest.getMovieTitle(), screenRequest.getStarTime());

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeAllScreen() {
        try {
            entityTransaction.begin();

            screenDAO.removeAllScreens();

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
}
