package movie_reservation.services;

import movie_reservation.data.ReservationDTO;
import movie_reservation.data.ScreenDTO;
import movie_reservation.data.UserDTO;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    @Override
    public void makeReservation(ScreenDTO screen, UserDTO user) {

    }

    @Override
    public List<ReservationDTO> findReservationsByUser(String userName) {
        return null;
    }

    @Override
    public void cancelReservation(UserDTO user, ReservationDTO reservation) {

    }
}
