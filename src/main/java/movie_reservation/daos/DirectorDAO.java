package movie_reservation.daos;

import movie_reservation.entities.Director;
import movie_reservation.types.Filmography;

import java.util.List;

public interface DirectorDAO {
    void addDirector(Director director);
    Director findDirector(String directorName);
    List<Director> findDirectorByBirthPlace(String birthPlace);
    List<Director> findAllDirectors();
    void updateDirector(String directorName, Filmography filmography);
    void removeDirector(String directorName);
    void removeAllDirectors();
}
