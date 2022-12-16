package movie_reservation.services;

import movie_reservation.data.DirectorDTO;
import movie_reservation.response_dtos.DirectorResponse;
import movie_reservation.types.Filmography;

import java.util.List;

public interface DirectorService {
    void registerDirector(DirectorDTO directorDTO);
    DirectorResponse findDirectorByName(String DirectorName);
    List<DirectorResponse> findDirectorsByBirthPlace(String birthPlace);
    List<DirectorResponse> findAllDirectors();
    void updateDirectorFilmography(String directorName, Filmography filmography);
    void removeDirector(String directorName);
    void emClose();
}
