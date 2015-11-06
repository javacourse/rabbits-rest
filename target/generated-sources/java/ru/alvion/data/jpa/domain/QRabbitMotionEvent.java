package ru.alvion.data.jpa.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRabbitMotionEvent is a Querydsl query type for RabbitMotionEvent
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRabbitMotionEvent extends EntityPathBase<RabbitMotionEvent> {

    private static final long serialVersionUID = 975165393L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRabbitMotionEvent rabbitMotionEvent = new QRabbitMotionEvent("rabbitMotionEvent");

    public final NumberPath<Integer> distance = createNumber("distance", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<org.joda.time.DateTime> motionEnd = createDateTime("motionEnd", org.joda.time.DateTime.class);

    public final DateTimePath<org.joda.time.DateTime> motionStart = createDateTime("motionStart", org.joda.time.DateTime.class);

    public final QBigRabbit rabbit;

    public QRabbitMotionEvent(String variable) {
        this(RabbitMotionEvent.class, forVariable(variable), INITS);
    }

    public QRabbitMotionEvent(Path<? extends RabbitMotionEvent> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRabbitMotionEvent(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRabbitMotionEvent(PathMetadata<?> metadata, PathInits inits) {
        this(RabbitMotionEvent.class, metadata, inits);
    }

    public QRabbitMotionEvent(Class<? extends RabbitMotionEvent> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rabbit = inits.isInitialized("rabbit") ? new QBigRabbit(forProperty("rabbit")) : null;
    }

}

