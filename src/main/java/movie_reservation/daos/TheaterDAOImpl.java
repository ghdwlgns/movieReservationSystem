package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.QTheater;
import movie_reservation.entities.Theater;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TheaterDAOImpl implements TheaterDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QTheater qTheater;

    public TheaterDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qTheater = new QTheater("theater");
    }

    @Override
    public void addTheater(Theater theater) {
        entityManager.persist(theater);

    }

    @Override
    public Theater findTheater(String floor, String theaterName) {
        return queryFactory.selectFrom(qTheater)
                .where(qTheater.floor.eq(floor), qTheater.theaterName.eq(theaterName))
                .fetchFirst();
    }

    @Override
    public List<Theater> findAllTheaters() {
        return queryFactory.selectFrom(qTheater)
                .orderBy(qTheater.floor.asc(), qTheater.theaterName.asc())
                .fetch();
    }

    @Override
    public List<Theater> findTheatersByFloor(String floor) {
        return queryFactory.selectFrom(qTheater)
                .where(qTheater.floor.eq(floor))
                .orderBy(qTheater.theaterName.asc())
                .fetch();
    }

    @Override
    public void removeTheater(String floor, String theaterName) {
        Theater theaterFind = queryFactory.selectFrom(qTheater)
                .where(qTheater.floor.eq(floor), qTheater.theaterName.eq(theaterName))
                .fetchFirst();
        entityManager.remove(theaterFind);

        entityManager.flush();
    }

    @Override
    public void removeTheatersByFloor(String floor) {
        List<Theater> theaters = queryFactory.selectFrom(qTheater)
                .where(qTheater.floor.eq(floor))
                .fetch();

        for(Theater theater : theaters)
            entityManager.remove(theater);

        entityManager.flush();
    }

    @Override
    public void removeAllTheaters() {
        List<Theater> theaters = queryFactory.selectFrom(qTheater)
                .fetch();

        for(Theater theater : theaters)
            entityManager.remove(theater);

        entityManager.flush();
    }
}
