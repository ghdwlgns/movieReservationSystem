package movie_reservation.services;

import movie_reservation.data.ReservationDTO;
import movie_reservation.data.ScreenDTO;
import movie_reservation.response_dtos.ReservationResponse;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.State;

import java.util.List;

public interface ReservationService {
    /**
     * 예약(ScreenDTO, userDTO)
     * 예약 내역 조회
     * 예약 변경(취소)
     */
    void makeReservation(ScreenDTO screen, String userName, List<SeatNumber> seatNumbers);
    List<ReservationResponse> findReservationsByUser(String userName);
    List<ReservationResponse> findReservationsByState(String userName, State state);
    void cancelReservation(ReservationDTO reservation);
    void expireReservation(ReservationDTO reservation);
}
