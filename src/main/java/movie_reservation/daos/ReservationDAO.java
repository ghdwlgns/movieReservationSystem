package movie_reservation.daos;

import movie_reservation.entities.Reservation;

import java.util.List;

public interface ReservationDAO {
    /*
     * findReservationByUser: userName을 매개변수로 받고, 가장 최근 예매 내역을 가져옴
     * cancelReservation: 가장 최근 예매 내역 중에서 영화가 끝나지 않은 상태인 예매를 취소함(단, 그 예매는 취소상태가 아님)
     */

    void addReservation(Reservation reservation);
}
