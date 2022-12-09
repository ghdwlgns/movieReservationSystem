package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.Movie;
import movie_reservation.entities.QMovie;
import movie_reservation.types.Genre;

import javax.persistence.*;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private JPAQueryFactory queryFactory;
    private QMovie qMovie;

    public MovieDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("movie_reservation");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        queryFactory = new JPAQueryFactory(entityManager);
        qMovie = new QMovie("movie");
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
    public void addMovie(Movie movie) {
        try {
            entityTransaction.begin();

            entityManager.persist(movie);

            flushChange();
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public Movie findMovie(String movieTitle) {
        return queryFactory.selectFrom(qMovie)
                .where(qMovie.title.eq(movieTitle))
                .fetchFirst();
    }

    @Override
    public List<Movie> findAllMovies() {
        return queryFactory.selectFrom(qMovie)
                .orderBy(qMovie.title.asc())
                .fetch();
    }

    @Override
    public List<Movie> findMoviesByGenre(Genre genre) {
        return queryFactory.selectFrom(qMovie)
                .where(genreEq(genre))
                .orderBy(qMovie.title.asc())
                .fetch();
    }

    @Override
    public void updateMovieReleaseDate(String movieTitle, String releaseDateUpdate) {
        try {
            entityTransaction.begin();
            Movie movieFind = queryFactory.selectFrom(qMovie)
                    .where(qMovie.releaseDate.eq(movieTitle))
                    .fetchFirst();

            Movie movieUpdate = entityManager.find(Movie.class, movieFind.getId());
            movieUpdate.changeReleaseDate(releaseDateUpdate);

            flushChange();
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeMovie(String movieTitle) {
        try {
            entityTransaction.begin();
            Movie movieFind = queryFactory.selectFrom(qMovie)
                    .where(qMovie.title.eq(movieTitle))
                    .fetchFirst();

            entityManager.remove(movieFind);

            flushChange();
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeAllMovies() {
        List<Movie> movies = queryFactory.selectFrom(qMovie)
                .fetch();

        try {
            entityTransaction.begin();

            for(Movie movie : movies)
                entityManager.remove(movie);

            flushChange();
            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public BooleanExpression releaseDateContains(String year) {
        if(year == null || year.equals(""))
            return null;
        return QMovie.movie.releaseDate.contains(year);
    }

    @Override
    public BooleanExpression genreEq(Genre genre) {
        if(genre == null)
            return null;
        return QMovie.movie.genre.eq(genre);
    }
}
