package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActorRole is a Querydsl query type for ActorRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActorRole extends EntityPathBase<ActorRole> {

    private static final long serialVersionUID = -1867528625L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActorRole actorRole = new QActorRole("actorRole");

    public final QActor actor;

    public final EnumPath<movie_reservation.types.Casting> castingAs = createEnum("castingAs", movie_reservation.types.Casting.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMovie movie;

    public QActorRole(String variable) {
        this(ActorRole.class, forVariable(variable), INITS);
    }

    public QActorRole(Path<? extends ActorRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActorRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActorRole(PathMetadata metadata, PathInits inits) {
        this(ActorRole.class, metadata, inits);
    }

    public QActorRole(Class<? extends ActorRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.actor = inits.isInitialized("actor") ? new QActor(forProperty("actor"), inits.get("actor")) : null;
        this.movie = inits.isInitialized("movie") ? new QMovie(forProperty("movie"), inits.get("movie")) : null;
    }

}

