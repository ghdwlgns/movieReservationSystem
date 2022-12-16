package movie_reservation.types;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeatNumber is a Querydsl query type for SeatNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSeatNumber extends BeanPath<SeatNumber> {

    private static final long serialVersionUID = -18065676L;

    public static final QSeatNumber seatNumber = new QSeatNumber("seatNumber");

    public final StringPath col = createString("col");

    public final StringPath hang = createString("hang");

    public QSeatNumber(String variable) {
        super(SeatNumber.class, forVariable(variable));
    }

    public QSeatNumber(Path<? extends SeatNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeatNumber(PathMetadata metadata) {
        super(SeatNumber.class, metadata);
    }

}

