package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTheater is a Querydsl query type for Theater
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTheater extends EntityPathBase<Theater> {

    private static final long serialVersionUID = -1432820907L;

    public static final QTheater theater = new QTheater("theater");

    public final StringPath floor = createString("floor");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Screen, QScreen> screens = this.<Screen, QScreen>createList("screens", Screen.class, QScreen.class, PathInits.DIRECT2);

    public final ListPath<Seat, QSeat> seats = this.<Seat, QSeat>createList("seats", Seat.class, QSeat.class, PathInits.DIRECT2);

    public final StringPath theaterName = createString("theaterName");

    public QTheater(String variable) {
        super(Theater.class, forVariable(variable));
    }

    public QTheater(Path<? extends Theater> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTheater(PathMetadata metadata) {
        super(Theater.class, metadata);
    }

}

