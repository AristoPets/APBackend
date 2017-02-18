package com.AristoPets.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -127920257L;

    public static final QUser user = new QUser("user");

    public final BooleanPath archived = createBoolean("archived");

    public final StringPath authId = createString("authId");

    public final EnumPath<com.AristoPets.entity.enums.AuthType> authType = createEnum("authType", com.AristoPets.entity.enums.AuthType.class);

    public final StringPath city = createString("city");

    public final StringPath club = createString("club");

    public final BooleanPath contractOfSale = createBoolean("contractOfSale");

    public final StringPath country = createString("country");

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath nursery = createString("nursery");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath photo = createString("photo");

    public final StringPath socials = createString("socials");

    public final EnumPath<com.AristoPets.entity.enums.UserType> userType = createEnum("userType", com.AristoPets.entity.enums.UserType.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

