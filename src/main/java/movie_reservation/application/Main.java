package movie_reservation.application;

import movie_reservation.controller.MovieReservationController;
import movie_reservation.controller.SystemFindController;
import movie_reservation.controller.SystemRegisterController;
import movie_reservation.query.QueryIngredients;
import movie_reservation.types.Address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static SystemRegisterController registerController = new SystemRegisterController();
    private static SystemFindController findController = new SystemFindController();
    private static MovieReservationController reservationController = new MovieReservationController();

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        try {
            init();
        } catch(IOException e) {
            e.printStackTrace();
        }

        QueryIngredients.getInstance().emfClose();
    }

    private static void init() throws IOException {
//        registerController.systemSetup();

        while(true) {
            System.out.println("영화 예매 시스템입니다.");
            System.out.println("1. 사용자 등록");
            System.out.println("2. 사용자 예매내역 조회");
            System.out.println("3. 영화 목록 조회");
            System.out.println("4. 영화 예매");
            System.out.println("5. 종료");
            System.out.println("원하시는 메뉴를 선택해주세요: ");

            int i = Integer.parseInt(br.readLine());

            if(i == 5) {
                break;
            }

            system(i);
        }
    }

    private static void system(int i) throws IOException {
        switch(i) {
            case 1 -> {
                System.out.println("사용자의 정보를 입력해주세요(이름 나이 주소(예시: 서울시 탄탄대로12길 13): ");
                String[] userInfo = br.readLine().split(" ");
                String name = userInfo[0];
                Long age = Long.parseLong(userInfo[1]);
                Address address = new Address(userInfo[2], userInfo[3], userInfo[4]);

                registerController.registerNewUser(name, age, address);
            }
            case 2 -> {
                findController.findUserReservation();
            }
            case 3 -> {
                findController.searchMovie();
            }
            case 4 -> {
                System.out.println("사용자 이름을 입력해주세요: ");
                String userName = br.readLine();
                reservationController.reserveMovie(userName);
            }
        }
    }
}
