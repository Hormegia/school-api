package com.apolo.spring.database;

/**
 * Enum para identificar las operaciones que se pueden manejar en las consultas a base de datos
 * La implementación se toma de https://medium.com/backend-habit/spring-jpa-make-dynamic-where-using-predicate-and-criteria-84550dfaa182
 */

public enum SearchOperation {
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH,
    MATCH_END,
    IS_NOT_NULL,
    JOIN
}
