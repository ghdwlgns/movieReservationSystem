package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservedSeat is a Querydsl query type for ReservedSeat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservedSeat extends EntityPathBase<ReservedSeat> {

    private static final long serialVersionUID = -1550944119L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservedSeat reservedSeat = new QReservedSeat("reservedSeat");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReservation reservation;

    public final QScreen screen;

    public final QSeat seat;

    public QReservedSeat(String variable) {
        this(ReservedSeat.class, forVariable(variable), INITS);
    }

    public QReservedSeat(Path<? extends ReservedSeat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservedSeat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservedSeat(PathMetadata metadata, PathInits inits) {
        this(ReservedSeat.class, metadata, inits);
    }

    public QReservedSeat(Class<? extends ReservedSeat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
        this.screen = inits.isInitialized("screen") ? new QScreen(forProperty("screen"), inits.get("screen")) : null;
        this.seat = inits.isInitialized("seat") ? new QSeat(forProperty("seat"), inits.get("seat")) : null;
    }

}

