package movie_reservation.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.daos.*;
import movie_reservation.data.ActorRoleDTO;
import movie_reservation.data.MovieDTO;
import movie_reservation.data.ScreenDTO;
import movie_reservation.entities.*;
import movie_reservation.query.BooleanExpressions;
import movie_reservation.query.QueryIngredients;
import movie_reservation.request_dtos.MovieRequest;
import movie_reservation.response_dtos.MovieResponse;
import movie_reservation.types.Casting;
import movie_reservation.types.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {
    private MovieDAO movieDAO;
    private DirectorDAO directorDAO;
    private ActorDAO actorDAO;
    private ActorRoleDAO actorRoleDAO;
    private TheaterDAO theaterDAO;
    private ScreenDAO screenDAO;
    private QueryIngredients query;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private JPAQueryFactory queryFactory;
    private BooleanExpressions booleanExpressions;

    public MovieServiceImpl() {
        query = new QueryIngredients();
        entityManager = query.getEntityManager();
        entityTransaction = entityManager.getTransaction();
        queryFactory = new JPAQueryFactory(entityManager);
        movieDAO = new MovieDAOImpl(entityManager);
        directorDAO = new DirectorDAOImpl(entityManager);
        actorDAO = new ActorDAOImpl(entityManager);
        actorRoleDAO = new ActorRoleDAOImpl(entityManager);
        theaterDAO = new TheaterDAOImpl(entityManager);
        screenDAO = new ScreenDAOImpl(entityManager);
        booleanExpressions = new BooleanExpressions();
    }

    @Override
    public void registerMovie(MovieDTO movie) {
        try {
            entityTransaction.begin();
            Movie movieConverted = toMovie(movie);

            extractActorRole(movie, movieConverted);

            extractScreen(movie, movieConverted);

            movieDAO.addMovie(movieConverted);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public MovieResponse findMovieByTitle(String movieTitle) {
        Movie movie = null;

        movie = movieDAO.findMovie(movieTitle);

        return new MovieResponse(movie.getTitle(), movie.getReleaseDate(), movie.getGenre(), movie.getRunningTime());
    }

    @Override
    public List<MovieResponse> findMoviesBy(String directorName, String actorName, String year) {
        /*
         * actorName, directorName으로 actor, director 먼저 찾고
         * join 연산 실행
         */
        Director directorFind = directorDAO.findDirector(directorName);
        Actor actor = actorDAO.findActor(actorName);

        QMovie m = new QMovie("movie");
        QActorRole a = new QActorRole("actorRole");
        QDirector d = new QDirector("director");

        List<Movie> moviesFind = queryFactory.select(m)
                .from(a, m, d)
                .where(booleanExpressions.directorEq(directorFind),
                        booleanExpressions.actorEq(actor),
                        booleanExpressions.releaseDateContains(year))
                .orderBy(m.title.asc())
                .fetch();

        return moviesFind.stream()
                .map(Movie::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieResponse> findMoviesByGenre(Genre genre) {
        List<MovieResponse> movieResponseList = new ArrayList<>();
        List<Movie> moviesByGenre = movieDAO.findMoviesByGenre(genre);

        return moviesByGenre.stream()
                .map(Movie::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieResponse> findAllMovies() {
        List<Movie> allMovies = movieDAO.findAllMovies();

        return allMovies.stream()
                .map(Movie::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void removeMovie(MovieRequest movie) {
        try {
            entityTransaction.begin();
            movieDAO.removeMovie(movie.getMovieTitle());
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    private Movie toMovie(MovieDTO movieDTO) {
        String title = movieDTO.getTitle();
        String releaseDate = movieDTO.getReleaseDate();
        Genre genre = movieDTO.getGenre();
        Long runningTime = movieDTO.getRunningTime();
        String directorName = movieDTO.getDirectorName();
        LocalDateTime dateCreated = movieDTO.getDateCreated();
        LocalDateTime dateModified = movieDTO.getDateModified();

        return new Movie(title, releaseDate, genre, runningTime, directorDAO.findDirector(directorName), dateCreated, dateModified);
    }

    private void extractScreen(MovieDTO movie, Movie movieConverted) {
        for(ScreenDTO screenDTO : movie.getScreens()) {
            Theater theater = theaterDAO.findTheater(screenDTO.getTheaterFloor(), screenDTO.getTheaterName());

            Screen screen = new Screen(movieConverted, theater, screenDTO.getStartTime(), screenDTO.getEndTime());

            screenDAO.addScreen(screen);
        }
    }

    private void extractActorRole(MovieDTO movie, Movie movieConverted) {
        for(ActorRoleDTO actorRoleDTO : movie.getActorRoles()) {
            Actor actor = actorDAO.findActor(actorRoleDTO.getActorName());
            Casting casting = actorRoleDTO.getCastingAs();
            ActorRole actorRole = new ActorRole(movieConverted, actor, casting);

            actorRoleDAO.addActorRole(actorRole);
        }
    }
}