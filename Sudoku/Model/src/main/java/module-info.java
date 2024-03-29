module Model {
    requires org.apache.commons.lang3;
    requires javafx.base;
    requires java.logging;
    requires slf4j.api;
    requires java.sql;

    exports pl.cp.model;
    exports pl.cp.observe;
    exports pl.cp.solver;
    exports pl.cp.dao;
    exports pl.cp.exception;
}