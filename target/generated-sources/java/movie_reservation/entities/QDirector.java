package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDirector is a Querydsl query type for Director
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDirector extends EntityPathBase<Director> {

    private static final long serialVersionUID = 1974915272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDirector director = new QDirector("director");

    public final QMovieStar _super;

    //inherited
    public final DatePath<java.time.LocalDate> birth;

    public final StringPath birthPlace = createString("birthPlace");

    // inherited
    public final movie_reservation.types.QFilmography filmography;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Movie, QMovie> moviesDirected = this.<Movie, QMovie>createList("moviesDirected", Movie.class, QMovie.class, PathInits.DIRECT2);

    //inherited
    public final StringPath name;

    public QDirector(String variable) {
        this(Director.class, forVariable(variable), INITS);
    }

    public QDirector(Path<? extends Director> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDirector(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDirector(PathMetadata metadata, PathInits inits) {
        this(Director.class, metadata, inits);
    }

    public QDirector(Class<? extends Director> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QMovieStar(type, metadata, inits);
        this.birth = _super.birth;
        this.filmography = _super.filmography;
        this.name = _super.name;
    }

}

