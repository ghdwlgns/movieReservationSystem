package movie_reservation.services;

import movie_reservation.data.SeatDTO;
import movie_reservation.request_dtos.ScreenRequest;
import movie_reservation.response_dtos.SeatResponse;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import java.util.List;

public interface SeatService {
    void registerSeats(List<SeatDTO> seatDTOList);
    List<SeatResponse> findSeatsByScreen(ScreenRequest screenRequest);
    List<SeatResponse> findAllSeats();
    void updateSeatState(List<SeatNumber> seatNumberList, SeatState seatState);
    void removeAllSeats();
}
