package ru.alvion.data.jpa.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRabbitPastureEvent is a Querydsl query type for RabbitPastureEvent
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRabbitPastureEvent extends EntityPathBase<RabbitPastureEvent> {

    private static final long serialVersionUID = -572144009L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRabbitPastureEvent rabbitPastureEvent = new QRabbitPastureEvent("rabbitPastureEvent");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<org.joda.time.DateTime> motionEnd = createDateTime("motionEnd", org.joda.time.DateTime.class);

    public final DateTimePath<org.joda.time.DateTime> motionStart = createDateTime("motionStart", org.joda.time.DateTime.class);

    public final EnumPath<PastureType> pastureType = createEnum("pastureType", PastureType.class);

    public final QBigRabbit rabbit;

    public QRabbitPastureEvent(String variable) {
        this(RabbitPastureEvent.class, forVariable(variable), INITS);
    }

    public QRabbitPastureEvent(Path<? extends RabbitPastureEvent> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRabbitPastureEvent(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRabbitPastureEvent(PathMetadata<?> metadata, PathInits inits) {
        this(RabbitPastureEvent.class, metadata, inits);
    }

    public QRabbitPastureEvent(Class<? extends RabbitPastureEvent> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rabbit = inits.isInitialized("rabbit") ? new QBigRabbit(forProperty("rabbit")) : null;
    }

}

