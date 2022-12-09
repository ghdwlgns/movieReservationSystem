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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovieStar movieStar = new QMovieStar("movieStar");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final movie_reservation.types.QFilmography filmography;

    public final StringPath name = createString("name");

    public QMovieStar(String variable) {
        this(MovieStar.class, forVariable(variable), INITS);
    }

    public QMovieStar(Path<? extends MovieStar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovieStar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovieStar(PathMetadata metadata, PathInits inits) {
        this(MovieStar.class, metadata, inits);
    }

    public QMovieStar(Class<? extends MovieStar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.filmography = inits.isInitialized("filmography") ? new movie_reservation.types.QFilmography(forProperty("filmography")) : null;
    }

}

