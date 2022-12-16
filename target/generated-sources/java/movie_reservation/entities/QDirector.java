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

    public static final QDirector director = new QDirector("director");

    public final QMovieStar _super = new QMovieStar(this);

    //inherited
    public final DatePath<java.time.LocalDate> birth = _super.birth;

    public final StringPath birthPlace = createString("birthPlace");

    //inherited
    public final ListPath<movie_reservation.types.Filmography, movie_reservation.types.QFilmography> filmographyList = _super.filmographyList;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Movie, QMovie> moviesDirected = this.<Movie, QMovie>createList("moviesDirected", Movie.class, QMovie.class, PathInits.DIRECT2);

    //inherited
    public final StringPath name = _super.name;

    public QDirector(String variable) {
        super(Director.class, forVariable(variable));
    }

    public QDirector(Path<? extends Director> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDirector(PathMetadata metadata) {
        super(Director.class, metadata);
    }

}

