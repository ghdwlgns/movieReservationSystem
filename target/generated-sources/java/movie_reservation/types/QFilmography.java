package movie_reservation.types;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFilmography is a Querydsl query type for Filmography
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFilmography extends BeanPath<Filmography> {

    private static final long serialVersionUID = 1644148624L;

    public static final QFilmography filmography = new QFilmography("filmography");

    public final EnumPath<Job> job = createEnum("job", Job.class);

    public final ListPath<String, StringPath> moviesParticipated = this.<String, StringPath>createList("moviesParticipated", String.class, StringPath.class, PathInits.DIRECT2);

    public QFilmography(String variable) {
        super(Filmography.class, forVariable(variable));
    }

    public QFilmography(Path<? extends Filmography> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilmography(PathMetadata metadata) {
        super(Filmography.class, metadata);
    }

}

