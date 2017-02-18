package com.AristoPets.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCookie is a Querydsl query type for UserCookie
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserCookie extends EntityPathBase<UserCookie> {

    private static final long serialVersionUID = -1769000253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCookie userCookie = new QUserCookie("userCookie");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath uuId = createString("uuId");

    public QUserCookie(String variable) {
        this(UserCookie.class, forVariable(variable), INITS);
    }

    public QUserCookie(Path<? extends UserCookie> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCookie(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCookie(PathMetadata metadata, PathInits inits) {
        this(UserCookie.class, metadata, inits);
    }

    public QUserCookie(Class<? extends UserCookie> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

