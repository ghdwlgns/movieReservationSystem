package movie_reservation.controller;

import movie_reservation.data.ScreenDTO;
import movie_reservation.request_dtos.ScreenRequest;
import movie_reservation.response_dtos.MovieResponse;
import movie_reservation.response_dtos.ScreenResponse;
import movie_reservation.response_dtos.SeatResponse;
import movie_reservation.services.*;
import movie_reservation.types.SeatNumber;
import movie_reservation.types.SeatState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MovieReservationController {
    private TheaterScreenService screenService;
    private ReservationService reservationService;
    private MovieService movieService;
    private SeatService seatService;

    public MovieReservationController() {
    }

    public void reserveMovie(String userName) {
        // 영화 목록 쭉 보여줌
        // 영화 목록 중 원하는 영화 선택
        // 영화 선택하면 상영 목록 나옴
        // 상영 목록 내용:
        /*
         *  영화 제목
         *  러닝 타임
         *  상영 정보(상영관, 시작 시간)
         *
         * 이 중에서 상영 정보 선택
         * 좌석 목록 나옴(당연히 예약된 좌석은 제외)
         * 좌석 선택(이건 좌석 번호로 선택함, 복수 선택 가능)
         *
         * 위의 정보들(상영 정보, 고른 좌석)로 영화 예매
         */
        screenService = new TheaterScreenServiceImpl();
        reservationService = new ReservationServiceImpl();
        movieService = new MovieServiceImpl();
        seatService = new SeatServiceImpl();

        MovieResponse movieResponse = showAllNSelectMovie();
        ScreenRequest screenRequest = showNSelectScreen(movieResponse.getMovieTitle());
        List<SeatNumber> seatNumbers = selectSeats(screenRequest);

        ScreenDTO screenBy = screenService.findScreenBy(screenRequest.getMovieTitle(),
                                                                screenRequest.getStarTime()).get(0).toDTO();

        reservationService.makeReservation(screenBy, userName, seatNumbers);
    }

    public MovieResponse showAllNSelectMovie() {
        screenService = new TheaterScreenServiceImpl();
        reservationService = new ReservationServiceImpl();
        movieService = new MovieServiceImpl();
        seatService = new SeatServiceImpl();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<MovieResponse> allMovies = movieService.findAllMovies();

        System.out.println("--현재 상영 중인 영화 목록--");

        for(MovieResponse movieResponse : allMovies) {
            System.out.println(movieResponse.getMovieTitle());
        }

        MovieResponse movie = null;
        System.out.println("예매하고 싶은 영화를 선택해주세요: ");

        try {
            String movieTitle = br.readLine();
            movie = movieService.findMovieByTitle(movieTitle);
        } catch(IOException e) {
            e.printStackTrace();
        }

        movieService.emClose();

        return movie;
    }

    public ScreenRequest showNSelectScreen(String movieTitle) {
        screenService = new TheaterScreenServiceImpl();
        reservationService = new ReservationServiceImpl();
        movieService = new MovieServiceImpl();
        seatService = new SeatServiceImpl();

        // 영화 조회 후 선택
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<ScreenResponse> screensBy = screenService.findScreenBy(movieTitle, null);
        System.out.println("--현재 상영 목록--");
        for(int i = 1;i <= screensBy.size();i++) {
            System.out.println(i + ". ");
            System.out.println(screensBy.get(i - 1).toString());
        }

        System.out.println("예매하고 싶은 상영을 선택해주세요: ");
        System.out.println("입력 예시: 3");
        ScreenResponse screenResponse = null;

        try {
            int i = Integer.parseInt(br.readLine());
            screenResponse = screensBy.get(i - 1);
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(screenResponse == null) {
            throw new NullPointerException();
        }

        screenService.emClose();

        return screenResponse.toRequest();
    }

    public List<SeatNumber> selectSeats(ScreenRequest screen) {
        screenService = new TheaterScreenServiceImpl();
        reservationService = new ReservationServiceImpl();
        movieService = new MovieServiceImpl();
        seatService = new SeatServiceImpl();

        List<SeatResponse> seatsByScreen = seatService.findSeatsByScreen(screen);

        int i = 1;

        for(SeatResponse seat : seatsByScreen) {
            System.out.println(i + ". " + seat);
            i++;
        }

        System.out.println("예매할 좌석을 선택해주세요: ");
        System.out.println("입력 예시(복수 선택 가능): 1 2 3");

        List<SeatNumber> seatNumbers = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] inputs = br.readLine().split(" ");

            for (String s : inputs) {
                SeatResponse seat = seatsByScreen.get(Integer.parseInt(s) - 1);
                if(seat.getState() == SeatState.AVAILABLE)
                    seatNumbers.add(seat.getSeatNumber());
                else {
                    System.out.println("잘못된 좌석을 선택하셨습니다. 다시 선택해주세요");
                    seatNumbers.clear();
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        seatService.emClose();

        return seatNumbers;
    }
}
