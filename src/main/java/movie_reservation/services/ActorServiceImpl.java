package movie_reservation.services;

import movie_reservation.daos.ActorDAO;
import movie_reservation.daos.ActorDAOImpl;
import movie_reservation.data.ActorDTO;
import movie_reservation.entities.Actor;
import movie_reservation.query.QueryIngredients;
import movie_reservation.response_dtos.ActorResponse;
import movie_reservation.types.Filmography;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;

public class ActorServiceImpl implements ActorService {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private QueryIngredients query;

    private ActorDAO actorDAO;

    public ActorServiceImpl() {
        query = new QueryIngredients();
        entityManager = query.getEntityManager();
        entityTransaction = entityManager.getTransaction();

        actorDAO = new ActorDAOImpl(entityManager);
    }

    @Override
    public void registerActor(ActorDTO actorDTO) {
        try {
            entityTransaction.begin();

            Actor actor = actorDTO.toActor();
            actorDAO.addActor(actor);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public ActorResponse findActor(String actorName) {
        return actorDAO.findActor(actorName).toResponse();
    }

    @Override
    public List<ActorResponse> findAllActors() {
        return actorDAO.findAllActors().stream()
                .map(Actor::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateActorFilmography(String actorName, Filmography filmography) {
        try {
            entityTransaction.begin();

            actorDAO.updateActor(actorName, filmography);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    @Override
    public void removeActor(String actorName) {
        try {
            entityTransaction.begin();

            actorDAO.removeActor(actorName);

            entityTransaction.commit();
        } catch(RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }
}
