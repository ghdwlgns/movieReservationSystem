package movie_reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUploadedTime is a Querydsl query type for UploadedTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUploadedTime extends EntityPathBase<UploadedTime> {

    private static final long serialVersionUID = -1627540343L;

    public static final QUploadedTime uploadedTime = new QUploadedTime("uploadedTime");

    public final DateTimePath<java.time.LocalDateTime> dateCreated = createDateTime("dateCreated", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> dateModified = createDateTime("dateModified", java.time.LocalDateTime.class);

    public QUploadedTime(String variable) {
        super(UploadedTime.class, forVariable(variable));
    }

    public QUploadedTime(Path<? extends UploadedTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadedTime(PathMetadata metadata) {
        super(UploadedTime.class, metadata);
    }

}

