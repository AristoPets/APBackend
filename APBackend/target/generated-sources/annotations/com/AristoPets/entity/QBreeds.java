package com.AristoPets.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBreeds is a Querydsl query type for Breeds
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBreeds extends EntityPathBase<Breeds> {

    private static final long serialVersionUID = 1077797939L;

    public static final QBreeds breeds = new QBreeds("breeds");

    public final EnumPath<com.AristoPets.entity.enums.AnimalType> animalType = createEnum("animalType", com.AristoPets.entity.enums.AnimalType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QBreeds(String variable) {
        super(Breeds.class, forVariable(variable));
    }

    public QBreeds(Path<? extends Breeds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBreeds(PathMetadata metadata) {
        super(Breeds.class, metadata);
    }

}

