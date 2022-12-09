package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActor is a Querydsl query type for Actor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActor extends EntityPathBase<Actor> {

    private static final long serialVersionUID = 463509049L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActor actor = new QActor("actor");

    public final QMovieStar _super;

    public final ListPath<ActorRole, QActorRole> actorRoles = this.<ActorRole, QActorRole>createList("actorRoles", ActorRole.class, QActorRole.class, PathInits.DIRECT2);

    //inherited
    public final DatePath<java.time.LocalDate> birth;

    // inherited
    public final movie_reservation.types.QFilmography filmography;

    public final NumberPath<java.math.BigDecimal> height = createNumber("height", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath instagramId = createString("instagramId");

    //inherited
    public final StringPath name;

    public QActor(String variable) {
        this(Actor.class, forVariable(variable), INITS);
    }

    public QActor(Path<? extends Actor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActor(PathMetadata metadata, PathInits inits) {
        this(Actor.class, metadata, inits);
    }

    public QActor(Class<? extends Actor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QMovieStar(type, metadata, inits);
        this.birth = _super.birth;
        this.filmography = _super.filmography;
        this.name = _super.name;
    }

}

