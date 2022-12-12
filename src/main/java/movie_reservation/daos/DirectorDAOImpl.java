package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.Director;
import movie_reservation.entities.QDirector;
import movie_reservation.types.Filmography;

import javax.persistence.*;
import java.util.List;

public class DirectorDAOImpl implements DirectorDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QDirector qDirector;

    public DirectorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qDirector = new QDirector("director");
    }
    @Override
    public void addDirector(Director director) {
        entityManager.persist(director);
    }

    @Override
    public Director findDirector(String directorName) {
        return queryFactory.selectFrom(qDirector)
                .where(qDirector.name.eq(directorName))
                .fetchFirst();
    }

    @Override
    public List<Director> findDirectorByBirthPlace(String birthPlace) {
        return queryFactory.selectFrom(qDirector)
                .where(qDirector.birthPlace.eq(birthPlace))
                .orderBy(qDirector.name.asc())
                .fetch();
    }

    @Override
    public List<Director> findAllDirectors() {
        return queryFactory.selectFrom(qDirector)
                .orderBy(qDirector.name.asc())
                .fetch();
    }

    @Override
    public void updateDirector(String directorName, Filmography filmography) {
        Director director = queryFactory.selectFrom(qDirector)
                .where(qDirector.name.eq(directorName))
                .fetchFirst();

        director.addFilmography(filmography);

        entityManager.flush();
    }

    @Override
    public void removeDirector(String directorName) {
        Director directorFind = queryFactory.selectFrom(qDirector)
                .where(qDirector.name.eq(directorName))
                .fetchFirst();
        entityManager.remove(directorFind);
    }

    @Override
    public void removeAllDirectors() {
        List<Director> directors = queryFactory.selectFrom(qDirector)
                .fetch();

        for(Director director : directors) {
            entityManager.remove(director);
        }
    }
}
