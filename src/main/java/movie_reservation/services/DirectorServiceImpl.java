package movie_reservation.services;

import movie_reservation.daos.DirectorDAO;
import movie_reservation.daos.DirectorDAOImpl;
import movie_reservation.data.DirectorDTO;
import movie_reservation.entities.Director;
import movie_reservation.query.QueryIngredients;
import movie_reservation.response_dtos.DirectorResponse;
import movie_reservation.types.Filmography;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;

public class DirectorServiceImpl implements DirectorService {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private QueryIngredients query;

    private DirectorDAO directorDAO;

    public DirectorServiceImpl() {
        query = new QueryIngredients();
        entityManager = query.getEntityManager();
        entityTransaction = entityManager.getTransaction();

        directorDAO = new DirectorDAOImpl(entityManager);
    }

    @Override
    public void registerDirector(DirectorDTO directorDTO) {
        try {
            entityTransaction.begin();

            directorDAO.addDirector(directorDTO.toDirector());

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public DirectorResponse findDirectorByName(String DirectorName) {
        return directorDAO.findDirector(DirectorName).toResponse();
    }

    @Override
    public List<DirectorResponse> findDirectorsByBirthPlace(String birthPlace) {
        return directorDAO.findDirectorByBirthPlace(birthPlace).stream()
                .map(Director::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DirectorResponse> findAllDirectors() {
        return directorDAO.findAllDirectors().stream()
                .map(Director::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateDirectorFilmography(String directorName, Filmography filmography) {
        try {
            entityTransaction.begin();

            directorDAO.updateDirector(directorName, filmography);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeDirector(String directorName) {
        try {
            entityTransaction.begin();

            directorDAO.removeDirector(directorName);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }
}
