package com.AristoPets.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdvertItem is a Querydsl query type for AdvertItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdvertItem extends EntityPathBase<AdvertItem> {

    private static final long serialVersionUID = 1467738555L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdvertItem advertItem = new QAdvertItem("advertItem");

    public final QAdvert advert;

    public final StringPath color = createString("color");

    public final EnumPath<com.AristoPets.entity.enums.Gender> gender = createEnum("gender", com.AristoPets.entity.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<String, StringPath> photos = this.<String, StringPath>createList("photos", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<com.AristoPets.entity.enums.AdvertItemState> state = createEnum("state", com.AristoPets.entity.enums.AdvertItemState.class);

    public QAdvertItem(String variable) {
        this(AdvertItem.class, forVariable(variable), INITS);
    }

    public QAdvertItem(Path<? extends AdvertItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdvertItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdvertItem(PathMetadata metadata, PathInits inits) {
        this(AdvertItem.class, metadata, inits);
    }

    public QAdvertItem(Class<? extends AdvertItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.advert = inits.isInitialized("advert") ? new QAdvert(forProperty("advert"), inits.get("advert")) : null;
    }

}

