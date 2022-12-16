package movie_reservation.controller;

import movie_reservation.data.*;
import movie_reservation.services.*;
import movie_reservation.types.Address;
import movie_reservation.types.Casting;
import movie_reservation.types.Genre;
import movie_reservation.types.SeatNumber;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SystemRegisterController {
    /**
     * 초기 셋업 및 신규 엔티티 등록 컨트롤러
     * 초기 셋업: 영화 4건, 감독 5명, 배우 6명, 사용자 1명, 상영관 4개(1층 1, 2상영관, 2층 1, 2상영관), 좌석 10개
     */
    private final String dirPath = System.getProperty("user.dir") + "\\src\\main\\resources\\files";

    private UserService userService;
    private MovieService movieService;
    private DirectorService directorService;
    private ActorService actorService;
    private TheaterScreenService theaterService;
    private TheaterScreenService screenService;
    private SeatService seatService;

    private File filesDirectory = new File(dirPath);
    private List<File> directorFileList;
    private List<File> actorFileList;
    private List<File> movieFileList;
    private List<File> theaterFileList;
    private List<File> screenFileList;
    private List<File> seatFileList;
    private List<File> userFileList;


    public SystemRegisterController() {
        directorFileList = Arrays.stream(Objects.requireNonNull(filesDirectory.listFiles((pathname, name)
                -> name.endsWith(".director")))).toList();

        actorFileList = Arrays.stream(Objects.requireNonNull(filesDirectory.listFiles((pathname, name)
                -> name.endsWith(".actor")))).toList();

        movieFileList = Arrays.stream(Objects.requireNonNull(filesDirectory.listFiles((pathname, name)
                -> name.endsWith(".movie")))).toList();

        theaterFileList = Arrays.stream(Objects.requireNonNull(filesDirectory.listFiles((pathname, name)
                -> name.endsWith(".theater")))).toList();

        screenFileList = Arrays.stream(Objects.requireNonNull(filesDirectory.listFiles((pathname, name)
                -> name.endsWith(".screen")))).toList();

        seatFileList = Arrays.stream(Objects.requireNonNull(filesDirectory.listFiles((pathname, name)
                -> name.endsWith(".seat")))).toList();

        userFileList = Arrays.stream(Objects.requireNonNull(filesDirectory.listFiles((pathname, name)
                -> name.endsWith(".user")))).toList();
    }

    public void systemSetup() {
        /*
         * 1. fileStream으로 파일 읽어옴(내용 자체는 텍스트 파일)
         * 2. 파일명(공백으로 구분) 파싱해서 확장자명으로 엔티티 구분(ex: .movie)
         * 3. 텍스트 파일 내용 파싱해서 등록
         */

        setupDirectors();
        setupActors();
        setupTheaters();
        setupMovies();
        setupScreens();
        setupSeats();
        setupUsers();
    }

    public void registerNewUser(String name, Long age, Address address) {
        userService = new UserServiceImpl();
        UserDTO userDTO = new UserDTO(name, age, address, LocalDateTime.now(), LocalDateTime.now());

        userService.registerUser(userDTO);

        System.out.println("새로운 사용자가 등록되었습니다!");
    }

    private void setupDirectors() {
        directorService = new DirectorServiceImpl();

        try {
            registerNewDirectors();
            directorService.emClose();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setupActors() {
        actorService = new ActorServiceImpl();

        try {
            registerNewActors();
            actorService.emClose();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setupTheaters() {
        theaterService = new TheaterScreenServiceImpl();

        try {
            registerNewTheaters();
            theaterService.emClose();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setupMovies() {
        movieService = new MovieServiceImpl();

        try {
            registerNewMovies();
            movieService.emClose();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setupScreens() {
        screenService = new TheaterScreenServiceImpl();

        try {
            registerNewScreens();
            screenService.emClose();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setupSeats() {
        seatService = new SeatServiceImpl();

        try {
            registerNewSeats();
            seatService.emClose();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setupUsers() {
        userService = new UserServiceImpl();

        try {
            registerNewUsers();
            seatService.emClose();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void registerNewDirectors() throws IOException {
        for(File directorFile : directorFileList) {
            BufferedReader reader = new BufferedReader(
                    new FileReader(directorFile.getPath())
            );

            String[] content = reader.readLine().split(" ");


            String name = content[0];
            String birth = content[1];
            String birthPlace;
            birthPlace = extractBirthPlace(content);

            DirectorDTO directorDTO = new DirectorDTO(name, birth, new ArrayList<>(), birthPlace);

            directorService.registerDirector(directorDTO);

            reader.close();
        }
    }

    private static String extractBirthPlace(String[] content) {
        String birthPlace;
        if(content.length > 2) {
            StringBuilder tmp = new StringBuilder();
            for(int i = 2; i < content.length; i++) {
                tmp.append(content[i]);
                if(i < content.length - 1)
                    tmp.append(" ");
            }

            birthPlace = tmp.toString();
        }
        else
            birthPlace = content[2];
        return birthPlace;
    }

    private void registerNewActors() throws IOException {
        for(File actorFile : actorFileList) {
            BufferedReader reader = new BufferedReader(
                    new FileReader(actorFile.getPath())
            );

            String[] content = reader.readLine().split(" ");

            for(String s : content)
                System.out.println(s);

            String name = content[0];
            String birth = content[1];
            BigDecimal height = BigDecimal.valueOf(Integer.parseInt(content[2]));
            String instagramId = content[3];

            if(instagramId.equals("없음"))
                instagramId = null;

            ActorDTO actorDTO = new ActorDTO(name, birth, height, instagramId);

            actorService.registerActor(actorDTO);

            reader.close();
        }
    }

    private void registerNewTheaters() throws IOException {
        for(File theaterFile : theaterFileList) {
            BufferedReader reader = new BufferedReader(
                    new FileReader(theaterFile.getPath())
            );

            String[] content = reader.readLine().split(" ");
            String floor = content[0];
            String name = content[1];

            TheaterDTO theaterDTO = new TheaterDTO(name, floor);

            theaterService.registerTheater(theaterDTO);

            reader.close();
        }
    }

    private void registerNewMovies() throws IOException {
        for(File movieFile : movieFileList) {
            List<ActorRoleDTO> actorRoleDTOList = new ArrayList<>();

            BufferedReader reader = new BufferedReader(
                    new FileReader(movieFile.getPath())
            );

            String[] exceptActors = reader.readLine().split(" ");
            String[] mainActors = reader.readLine().split(" ");
            String [] subActors = reader.readLine().split(" ");

            String title = exceptActors[0];
            String releaseDate = exceptActors[1];

            for(String mainActor : mainActors) {
                ActorRoleDTO mainActorRole = new ActorRoleDTO(mainActor, title, Casting.MAIN_ROLE);
                actorRoleDTOList.add(mainActorRole);
            }

            for(String subActor : subActors) {
                ActorRoleDTO subActorRole = new ActorRoleDTO(subActor, title, Casting.SUB_ROLE);
                actorRoleDTOList.add(subActorRole);
            }

            String directorName = exceptActors[2];
            Genre genre = Genre.valueOf(exceptActors[3]);
            Long runningTime = Long.parseLong(exceptActors[4]);

            MovieDTO movieDTO = new MovieDTO(title, releaseDate, actorRoleDTOList, directorName, genre, runningTime, LocalDateTime.now(), LocalDateTime.now());
            movieService.registerMovie(movieDTO);

            reader.close();
        }
    }

    private void registerNewScreens() throws IOException {
        for(File screenFile : screenFileList) {
            BufferedReader reader = new BufferedReader(
                    new FileReader(screenFile.getPath())
            );
            String[] contents = reader.readLine().split(" ");
            String movieTitle = contents[0];
            String floor = contents[1];
            String theaterName = contents[2];
            LocalTime startTime = LocalTime.parse(contents[3]);
            LocalTime endTime = LocalTime.parse(contents[4]);

            ScreenDTO screenDTO = new ScreenDTO(movieTitle, floor, theaterName, startTime, endTime);
            screenService.registerScreen(screenDTO);

            reader.close();
        }
    }

    private void registerNewSeats() throws IOException {
        for(File seatFile : seatFileList) {
            BufferedReader reader = new BufferedReader(
                    new FileReader(seatFile.getPath())
            );
            String[] contents = reader.readLine().split(" ");
            TheaterDTO theaterDTO = new TheaterDTO(contents[1], contents[0]);
            SeatNumber seatNumber = new SeatNumber(contents[2], contents[3]);

            SeatDTO seatDTO = new SeatDTO(theaterDTO, seatNumber);

            seatService.registerSeat(seatDTO);
        }
    }

    private void registerNewUsers() throws IOException {
        for(File userFile : userFileList) {
            BufferedReader reader = new BufferedReader(
                    new FileReader(userFile.getPath())
            );
            String[] contents = reader.readLine().split(" ");
            String userName = contents[0];
            Long age = Long.parseLong(contents[1]);
            Address address = new Address(contents[2], contents[3], contents[4]);

            userService.registerUser(new UserDTO(userName, age, address, LocalDateTime.now(), LocalDateTime.now()));
        }
    }
}