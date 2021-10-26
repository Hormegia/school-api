package com.apolo.spring.database;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que permite generar consultas dinámicas para todas las entidades que se manejen
 * La idea base se toma de https://medium.com/backend-habit/spring-jpa-make-dynamic-where-using-predicate-and-criteria-84550dfaa182
 */

public class GenericSpecification<T> implements Specification<T> {

    private List<SearchCriteria> list;
    private HashMap<String, Join<Object, Object>> helperPathsForJoins;

    private Root<T> root;

    public GenericSpecification() {
        this.list = new ArrayList<>();
        this.helperPathsForJoins = new HashMap<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        this.root = root;
        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {

            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {

                if (criteria.getValue().getClass().isAssignableFrom(Date.class))
                    predicates.add(builder.greaterThan(
                            getCorrectPath(criteria.getKey()), new java.sql.Date(criteria.getDate().getTime())));
                else
                    predicates.add(builder.greaterThan(
                            getCorrectPath(criteria.getKey()), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {

                if (criteria.getValue().getClass().isAssignableFrom(Date.class))
                    predicates.add(builder.lessThan(
                            getCorrectPath(criteria.getKey()), new java.sql.Date(criteria.getDate().getTime())));
                else
                    predicates.add(builder.lessThan(
                            getCorrectPath(criteria.getKey()), criteria.getValue().toString()));


            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {

                if (criteria.getValue().getClass().isAssignableFrom(Date.class))
                    predicates.add(builder.greaterThanOrEqualTo(
                            getCorrectPath(criteria.getKey()), new java.sql.Date(criteria.getDate().getTime())));
                else
                    predicates.add(builder.greaterThanOrEqualTo(
                            getCorrectPath(criteria.getKey()), criteria.getValue().toString()));


            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {

                if (criteria.getValue().getClass().isAssignableFrom(Date.class))
                    predicates.add(builder.lessThanOrEqualTo(
                            getCorrectPath(criteria.getKey()), new java.sql.Date(criteria.getDate().getTime())));
                else
                    predicates.add(builder.lessThanOrEqualTo(
                            getCorrectPath(criteria.getKey()), criteria.getValue().toString()));


            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {

                if (criteria.getValue().getClass().isAssignableFrom(Date.class))
                    predicates.add(builder.notEqual(
                            getCorrectPath(criteria.getKey()), new java.sql.Date(criteria.getDate().getTime())));
                else
                    predicates.add(builder.notEqual(
                            getCorrectPath(criteria.getKey()), criteria.getValue()));


            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {

                if (criteria.getValue().getClass().isAssignableFrom(Date.class))
                    predicates.add(builder.equal(
                            getCorrectPath(criteria.getKey()), new java.sql.Date(criteria.getDate().getTime())));
                else
                    predicates.add(builder.equal(
                            getCorrectPath(criteria.getKey()), criteria.getValue()));


            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {


                predicates.add(builder.like(
                        builder.lower(getCorrectPath(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));


            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {


                predicates.add(builder.like(
                        builder.lower(getCorrectPath(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));


            } else if (criteria.getOperation().equals(SearchOperation.IS_NOT_NULL)) {

                predicates.add(builder.isNotNull(getCorrectPath(criteria.getKey())));


            } else if (criteria.getOperation().equals(SearchOperation.JOIN)) {

                String[] joins = criteria.getJoins();
                Join<Object, Object> actualTable = null;
                for (String tableName : joins) {
                    tableName = tableName.toLowerCase();
                    System.err.println(tableName);
                    if (actualTable == null)
                        actualTable = root.join(tableName);
                    else
                        actualTable = actualTable.join(tableName);

                    if (!helperPathsForJoins.containsKey(tableName)) {
                        helperPathsForJoins.put(tableName, actualTable);
                    }

                }
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private <Y> Path<Y> getCorrectPath(String key) {
        String[] arrayClass = key.split("\\.");

        if (arrayClass.length == 1) {

            return root.get(arrayClass[0]);
        } else {

            return helperPathsForJoins.get(arrayClass[0]).get(arrayClass[1]);
        }
    }
}

