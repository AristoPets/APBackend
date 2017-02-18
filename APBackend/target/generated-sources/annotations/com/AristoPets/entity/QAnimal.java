package com.AristoPets.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnimal is a Querydsl query type for Animal
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAnimal extends EntityPathBase<Animal> {

    private static final long serialVersionUID = 1045601456L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnimal animal = new QAnimal("animal");

    public final BooleanPath archived = createBoolean("archived");

    public final DatePath<java.util.Date> birthday = createDate("birthday", java.util.Date.class);

    public final QBreeds breed;

    public final NumberPath<Integer> breedId = createNumber("breedId", Integer.class);

    public final StringPath club = createString("club");

    public final StringPath color = createString("color");

    public final EnumPath<com.AristoPets.entity.enums.Gender> gender = createEnum("gender", com.AristoPets.entity.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath moreInfo = createString("moreInfo");

    public final StringPath name = createString("name");

    public final StringPath nursery = createString("nursery");

    public final ListPath<String, StringPath> photos = this.<String, StringPath>createList("photos", String.class, StringPath.class, PathInits.DIRECT2);

    public final BooleanPath readyToCopulation = createBoolean("readyToCopulation");

    public final ListPath<String, StringPath> titles = this.<String, StringPath>createList("titles", String.class, StringPath.class, PathInits.DIRECT2);

    public final QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAnimal(String variable) {
        this(Animal.class, forVariable(variable), INITS);
    }

    public QAnimal(Path<? extends Animal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnimal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnimal(PathMetadata metadata, PathInits inits) {
        this(Animal.class, metadata, inits);
    }

    public QAnimal(Class<? extends Animal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.breed = inits.isInitialized("breed") ? new QBreeds(forProperty("breed")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

