package movie_reservation.daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie_reservation.entities.QScreen;
import movie_reservation.entities.Screen;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalTime;
import java.util.List;

public class ScreenDAOImpl implements ScreenDAO {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private QScreen qScreen;

    public ScreenDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(this.entityManager);
        qScreen = new QScreen("screen");
    }

    @Override
    public void addScreen(Screen screen) {
        entityManager.persist(screen);
    }

    @Override
    public List<Screen> findScreens() {
        return queryFactory.selectFrom(qScreen)
                .orderBy(qScreen.startTime.asc())
                .fetch();
    }

    @Override
    public List<Screen> findScreensByStartTime(String startTime) {
        LocalTime localStartTime = stringToLocalTime(startTime);

        return queryFactory.selectFrom(qScreen)
                .where(qScreen.startTime.eq(localStartTime))
                .orderBy(qScreen.movie.title.asc())
                .fetch();
    }

    @Override
    public List<Screen> findScreensByMovieTitle(String movieTitle) {
        return queryFactory.selectFrom(qScreen)
                .where(qScreen.movie.title.eq(movieTitle))
                .orderBy(qScreen.movie.title.asc())
                .fetch();
    }

    private LocalTime stringToLocalTime(String time) {
        // String format: hh시 mm분(초는 무조건 0.0)
        String[] timeSplit = time.split("시 |분 |초 ");
        StringBuilder sb = new StringBuilder();
        sb.append(timeSplit[0]).append(":").append(timeSplit[1]).append(":").append("0");
        String timeParsed = sb.toString();

        return LocalTime.parse(timeParsed);
    }
}
