package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.*;
import movie_reservation.types.Genre;

import javax.persistence.*;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QMovie qMovie;

    public MovieDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qMovie = new QMovie("movie");
    }

    @Override
    public void addMovie(Movie movie) {
        entityManager.persist(movie);
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
                .where(qMovie.genre.eq(genre))
                .orderBy(qMovie.title.asc())
                .fetch();
    }

    @Override
    public List<Movie> findMoviesBy(Director director, ActorRole actorRole, String year) {
        return null;
    }

    @Override
    public void updateMovieReleaseDate(String movieTitle, String releaseDateUpdate) {
        Movie movieFind = queryFactory.selectFrom(qMovie)
                .where(qMovie.releaseDate.eq(movieTitle))
                .fetchFirst();

        movieFind.changeReleaseDate(releaseDateUpdate);

        entityManager.flush();
    }

    @Override
    public void removeMovie(String movieTitle) {
        Movie movieFind = queryFactory.selectFrom(qMovie)
                .where(qMovie.title.eq(movieTitle))
                .fetchFirst();

        Movie movieRemove = entityManager.find(Movie.class, movieFind.getId());

        entityManager.remove(movieRemove);
    }

    @Override
    public void removeAllMovies() {
        List<Movie> movies = queryFactory.selectFrom(qMovie)
                .fetch();

        for(Movie movie : movies) {
            Movie movieRemove = entityManager.find(Movie.class, movie.getId());

            entityManager.remove(movieRemove);
        }
    }
}
