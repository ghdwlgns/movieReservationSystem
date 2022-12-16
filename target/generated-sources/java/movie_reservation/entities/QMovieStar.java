package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovieStar is a Querydsl query type for MovieStar
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QMovieStar extends EntityPathBase<MovieStar> {

    private static final long serialVersionUID = -1051997210L;

    public static final QMovieStar movieStar = new QMovieStar("movieStar");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final ListPath<movie_reservation.types.Filmography, movie_reservation.types.QFilmography> filmographyList = this.<movie_reservation.types.Filmography, movie_reservation.types.QFilmography>createList("filmographyList", movie_reservation.types.Filmography.class, movie_reservation.types.QFilmography.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QMovieStar(String variable) {
        super(MovieStar.class, forVariable(variable));
    }

    public QMovieStar(Path<? extends MovieStar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovieStar(PathMetadata metadata) {
        super(MovieStar.class, metadata);
    }

}

