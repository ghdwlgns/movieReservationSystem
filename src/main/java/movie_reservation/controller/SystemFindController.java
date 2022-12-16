package movie_reservation.controller;

import movie_reservation.response_dtos.*;
import movie_reservation.services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SystemFindController {
/*
 * 2) 배우 / 감독 검색(이름으로)
 * 3) 영화 예매
 *      i) 영화 이름으로 상영 정보 조회 / 시간으로 상영 정보 조회 / 둘 다로 상영 정보 조회
 *      ii) 조회된 상영 정보들 중에서 하나 선택
 *      iii) 선택된 상영 정보의 좌석 정보 출력(예약된 좌석은 제외하고 출력)
 *      iv) 사용자가 예약하고 싶은 좌석 선택(복수 가능)
 *      v) 위의 과정 통한 정보들 ReservationDTO로 만들어 전송(이때 state는 RESERVED)
 * 4) 영화 조회(배우 / 감독 / 출시년도)
 */

    private ActorService actorService;
    private DirectorService directorService;
    private MovieService movieService;
    private UserService userService;
    private ReservationService reservationService;

    public SystemFindController() {

    }

    public void searchActor(String actorName) {
        actorService = new ActorServiceImpl();

        ActorResponse actor = actorService.findActor(actorName);
        System.out.println(actor);
        actorService.emClose();

    }

    public void searchDirector(String directorName) {
        directorService = new DirectorServiceImpl();

        DirectorResponse director = directorService.findDirectorByName(directorName);
        System.out.println(director);
        directorService.emClose();
    }

    public void searchMovie() {
        movieService = new MovieServiceImpl();

        System.out.println("1. 영화 제목으로 검색");
        System.out.println("2. 배우 이름으로 검색");
        System.out.println("3. 감독 이름으로 검색");
        System.out.println("4. 출시년도로 검색");
        System.out.println("5. 배우 이름과 감독이름으로 검색");
        System.out.println("6. 배우 이름과 출시년도로 검색");
        System.out.println("7. 감독 이름과 출시년도로 검색");
        System.out.println("8. 세 조건 모두로 검색");
        System.out.println("검색 조건을 선택해주세요: ");

        List<MovieResponse> movieResponseList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int i = Integer.parseInt(br.readLine());
            String s = br.readLine();
            if(i > 1)
                movieResponseList = findMovieByOption(i, s);
            else
                movieResponseList.add(findMovieByOption1(s));
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(movieResponseList.isEmpty()) {
            System.out.println("검색 결과가 없습니다");
        }
        else {
            System.out.println("--현재 등록된 영화 목록--");
            for(MovieResponse movieResponse : movieResponseList) {
                System.out.println(movieResponse);
            }
        }

        movieService.emClose();
    }

    private MovieResponse findMovieByOption1(String s) {
        System.out.println("입력 예시: 바람과 함께 사라지다");
        return movieService.findMovieByTitle(s);
    }

    private List<MovieResponse> findMovieByOption(int i, String s) {
        List<MovieResponse> movieResponseList = new ArrayList<>();

        switch(i) {
            case 2 -> {
                System.out.println("입력 예시: 홍길동");
                movieResponseList = movieService.findMoviesBy("", s, "");
            }
            case 3 -> {
                System.out.println("입력 예시: 홍길동");
                movieResponseList = movieService.findMoviesBy(s, "", "");
            }
            case 4 -> {
                System.out.println("입력 예시: 2022");
                movieResponseList = movieService.findMoviesBy("", "", s);
            }
            case 5 -> {
                System.out.println("입력 예시(배우 이름을 먼저 입력해주세요!): 김첨지 김갑자");
                String[] actorNDirectorName = s.split(" ");
                movieResponseList = movieService.findMoviesBy(actorNDirectorName[1], actorNDirectorName[0], "");
            }
            case 6 -> {
                System.out.println("입력 예시(배우 이름을 먼저 입력해주세요!): 김첨지 2022");
                String[] nameNYear = s.split(" ");
                movieResponseList = movieService.findMoviesBy("", nameNYear[0], nameNYear[1]);
            }
            case 7 -> {
                System.out.println("입력 예시(감독 이름을 먼저 입력해주세요!: 김갑자 2022");
                String[] nameNYear = s.split(" ");
                movieResponseList = movieService.findMoviesBy(nameNYear[0], "", nameNYear[1]);
            }
            case 8 -> {
                System.out.println("입력 예시(배우, 감독, 년도 순으로 입력해주세요!): 김첨지 김갑자 2022");
                String[] nameNYear = s.split(" ");
                movieResponseList = movieService.findMoviesBy(nameNYear[1], nameNYear[0], nameNYear[2]);
            }
        }

        return movieResponseList;
    }

    public void findUserReservation() throws IOException {
        userService = new UserServiceImpl();
        reservationService = new ReservationServiceImpl();
        List<UserResponse> allUser = userService.findAllUser();

        int i = 1;
        System.out.println("현재 등록되어 있는 사용자의 목록은 다음과 같습니다.");
        for(UserResponse userResponse : allUser) {
            System.out.println(i + ". " + userResponse.getUserName());
            i++;
        }
        System.out.println("어떤 사용자의 예매 내역을 확인하시겠습니까?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        System.out.println("사용자의 예매 내역은 다음과 같습니다");
        List<ReservationResponse> reservationsByUser = reservationService.findReservationsByUser(allUser.get(input - 1).getUserName());
        for(ReservationResponse reservationResponse : reservationsByUser) {
            System.out.println(reservationResponse);
        }

    }
}
