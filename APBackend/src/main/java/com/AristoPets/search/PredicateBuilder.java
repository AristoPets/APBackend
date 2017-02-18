package com.AristoPets.search;


import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;

public class PredicateBuilder<T> {

    private List<SearchCriteria> searchParams;


    public PredicateBuilder() {
        searchParams = new ArrayList<>();
    }

    public PredicateBuilder with(String key, String operation, String value){
        searchParams.add(new SearchCriteria(key,operation,value));
        return this;
    }

    public BooleanExpression build(Class<T> entityClass, String entityName){
        if(searchParams.size() == 0){
            return null;
        }
        CustomPredicate<T> predicate = new CustomPredicate<>(entityClass,entityName);
        List<BooleanExpression> predicates = new ArrayList<>();
        for(SearchCriteria param : searchParams){
            predicate.setCriteria(param);
            BooleanExpression exp = null;
            try {
                exp = predicate.getPredicate();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            if (exp != null){
                predicates.add(exp);
            }
        }
        BooleanExpression result = predicates.get(0);
        for(int i = 1; i < predicates.size(); i++){
            result = result.and(predicates.get(i));
        }
        return result;
    }
}
