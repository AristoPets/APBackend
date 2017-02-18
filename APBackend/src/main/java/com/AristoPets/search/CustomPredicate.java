package com.AristoPets.search;


import com.AristoPets.entity.enums.Gender;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import org.apache.commons.lang3.math.NumberUtils;

public class CustomPredicate<T> {

    private SearchCriteria criteria;
    private String entityName;
    private Class<T> entityClass;

    public CustomPredicate(Class<T> entityClass,String entityName) {
        this.entityName = entityName;
        this.entityClass = entityClass;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() throws Exception {
        PathBuilder<T> entityPath = new PathBuilder<>(entityClass, entityName);
        if (criteria == null) {
            throw new Exception("PREDICATE MUST HAVE AT LEAST ONE CRITERIA!");
        }
        String operation = criteria.getOperation();
//        if(criteria.getKey().equals("gender")){
//            EnumPath<?> enumPath = entityPath.getEnum((String) criteria.getValue(),Gender.class);
//            return enumPath.
//        }
        if (NumberUtils.isCreatable(criteria.getValue().toString())){
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            if (operation.equalsIgnoreCase("=")){
                return path.eq(value);
            }
            else if (operation.equalsIgnoreCase(">")){
                return path.goe(value);
            }
            else if (operation.equalsIgnoreCase("<")){
                return path.loe(value);
            }
        }
        else{
            StringPath path = entityPath.getString(criteria.getKey());
            if(operation.equalsIgnoreCase("=")){
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }
}