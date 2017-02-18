package com.AristoPets.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdvert is a Querydsl query type for Advert
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdvert extends EntityPathBase<Advert> {

    private static final long serialVersionUID = 1036746376L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdvert advert = new QAdvert("advert");

    public final ListPath<AdvertItem, QAdvertItem> advertItems = this.<AdvertItem, QAdvertItem>createList("advertItems", AdvertItem.class, QAdvertItem.class, PathInits.DIRECT2);

    public final BooleanPath archived = createBoolean("archived");

    public final NumberPath<Integer> averagePrice = createNumber("averagePrice", Integer.class);

    public final DatePath<java.util.Date> birthday = createDate("birthday", java.util.Date.class);

    public final QBreeds breed;

    public final NumberPath<Integer> breedId = createNumber("breedId", Integer.class);

    public final StringPath club = createString("club");

    public final StringPath description = createString("description");

    public final StringPath firstVaccination = createString("firstVaccination");

    public final NumberPath<Long> fParentId = createNumber("fParentId", Long.class);

    public final StringPath fParentRef = createString("fParentRef");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> mParentId = createNumber("mParentId", Long.class);

    public final StringPath mParentRef = createString("mParentRef");

    public final ListPath<String, StringPath> photos = this.<String, StringPath>createList("photos", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath secondVaccination = createString("secondVaccination");

    public final StringPath title = createString("title");

    public final QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAdvert(String variable) {
        this(Advert.class, forVariable(variable), INITS);
    }

    public QAdvert(Path<? extends Advert> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdvert(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdvert(PathMetadata metadata, PathInits inits) {
        this(Advert.class, metadata, inits);
    }

    public QAdvert(Class<? extends Advert> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.breed = inits.isInitialized("breed") ? new QBreeds(forProperty("breed")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

