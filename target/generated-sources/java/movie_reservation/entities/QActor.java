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

    public static final QActor actor = new QActor("actor");

    public final QMovieStar _super = new QMovieStar(this);

    public final ListPath<ActorRole, QActorRole> actorRoles = this.<ActorRole, QActorRole>createList("actorRoles", ActorRole.class, QActorRole.class, PathInits.DIRECT2);

    //inherited
    public final DatePath<java.time.LocalDate> birth = _super.birth;

    //inherited
    public final ListPath<movie_reservation.types.Filmography, movie_reservation.types.QFilmography> filmographyList = _super.filmographyList;

    public final NumberPath<java.math.BigDecimal> height = createNumber("height", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath instagramId = createString("instagramId");

    //inherited
    public final StringPath name = _super.name;

    public QActor(String variable) {
        super(Actor.class, forVariable(variable));
    }

    public QActor(Path<? extends Actor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActor(PathMetadata metadata) {
        super(Actor.class, metadata);
    }

}

