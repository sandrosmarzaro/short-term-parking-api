package one.digitalinnovation.shorttermparking.controllers;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerBase {
    static final PostgreSQLContainer POSTGRES_SQL_CONTAINER;

    static {
        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer("postgres:14-alpine");
        POSTGRES_SQL_CONTAINER.start();
        System.setProperty("spirng.datasource.url", POSTGRES_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("spirng.datasource.username", POSTGRES_SQL_CONTAINER.getUsername());
        System.setProperty("spirng.datasource.password", POSTGRES_SQL_CONTAINER.getPassword());
    }
}
