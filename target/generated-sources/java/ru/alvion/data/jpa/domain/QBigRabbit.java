package ru.alvion.data.jpa.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBigRabbit is a Querydsl query type for BigRabbit
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBigRabbit extends EntityPathBase<BigRabbit> {

    private static final long serialVersionUID = -1825214463L;

    public static final QBigRabbit bigRabbit = new QBigRabbit("bigRabbit");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath color = createString("color");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> mass = createNumber("mass", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath photoUrl = createString("photoUrl");

    public QBigRabbit(String variable) {
        super(BigRabbit.class, forVariable(variable));
    }

    public QBigRabbit(Path<? extends BigRabbit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBigRabbit(PathMetadata<?> metadata) {
        super(BigRabbit.class, metadata);
    }

}

