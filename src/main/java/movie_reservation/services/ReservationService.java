package movie_reservation.services;

import movie_reservation.data.ReservationDTO;
import movie_reservation.data.ScreenDTO;
import movie_reservation.data.UserDTO;

import java.util.List;

public interface ReservationService {
    /**
     * 예약(ScreenDTO, userDTO)
     * 예약 내역 조회
     * 예약 변경(취소)
     */
    void makeReservation(ScreenDTO screen, UserDTO user);
    List<ReservationDTO> findReservationsByUser(String userName);
    void cancelReservation(UserDTO user, ReservationDTO reservation);
}
