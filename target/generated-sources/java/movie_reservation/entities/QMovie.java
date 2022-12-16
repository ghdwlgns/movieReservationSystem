package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovie is a Querydsl query type for Movie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovie extends EntityPathBase<Movie> {

    private static final long serialVersionUID = 474950516L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovie movie = new QMovie("movie");

    public final QUploadedTime _super = new QUploadedTime(this);

    public final ListPath<ActorRole, QActorRole> actorRoles = this.<ActorRole, QActorRole>createList("actorRoles", ActorRole.class, QActorRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dateCreated = _super.dateCreated;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dateModified = _super.dateModified;

    public final QDirector director;

    public final EnumPath<movie_reservation.types.Genre> genre = createEnum("genre", movie_reservation.types.Genre.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath releaseDate = createString("releaseDate");

    public final NumberPath<Long> runningTime = createNumber("runningTime", Long.class);

    public final ListPath<Screen, QScreen> screens = this.<Screen, QScreen>createList("screens", Screen.class, QScreen.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QMovie(String variable) {
        this(Movie.class, forVariable(variable), INITS);
    }

    public QMovie(Path<? extends Movie> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovie(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovie(PathMetadata metadata, PathInits inits) {
        this(Movie.class, metadata, inits);
    }

    public QMovie(Class<? extends Movie> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.director = inits.isInitialized("director") ? new QDirector(forProperty("director")) : null;
    }

}

