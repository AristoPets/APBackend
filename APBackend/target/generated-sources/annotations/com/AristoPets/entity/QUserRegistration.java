package com.AristoPets.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRegistration is a Querydsl query type for UserRegistration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRegistration extends EntityPathBase<UserRegistration> {

    private static final long serialVersionUID = 977604056L;

    public static final QUserRegistration userRegistration = new QUserRegistration("userRegistration");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserRegistration(String variable) {
        super(UserRegistration.class, forVariable(variable));
    }

    public QUserRegistration(Path<? extends UserRegistration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRegistration(PathMetadata metadata) {
        super(UserRegistration.class, metadata);
    }

}

