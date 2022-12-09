package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import movie_reservation.entities.Director;

import java.util.List;

public class DirectorDAOImpl implements DirectorDAO {
    @Override
    public void addDirector(Director director) {

    }

    @Override
    public Director findDirector(String directorName) {
        return null;
    }

    @Override
    public List<Director> findAllDirectors() {
        return null;
    }

    @Override
    public void removeDirector(String directorName) {

    }

    @Override
    public void removeAllDirectors() {

    }

    @Override
    public BooleanExpression directorNameEq(String directorName) {
        return null;
    }
}
