package movie_reservation.daos;

import com.querydsl.core.types.dsl.BooleanExpression;
import movie_reservation.entities.Director;

import java.util.List;

public interface DirectorDAO {
    void addDirector(Director director);
    Director findDirector(String directorName);
    List<Director> findAllDirectors();
    void removeDirector(String directorName);
    void removeAllDirectors();
    BooleanExpression directorNameEq(String directorName);
}
