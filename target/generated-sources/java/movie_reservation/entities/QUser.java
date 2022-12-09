package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -122984697L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QUploadedTime _super = new QUploadedTime(this);

    public final movie_reservation.types.QAddress address;

    public final NumberPath<Long> age = createNumber("age", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dateCreated = _super.dateCreated;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dateModified = _super.dateModified;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Reservation, QReservation> reservationList = this.<Reservation, QReservation>createList("reservationList", Reservation.class, QReservation.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new movie_reservation.types.QAddress(forProperty("address")) : null;
    }

}

