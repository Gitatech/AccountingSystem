package com.bsu.accounting.system.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class DBConnector {

    private static final Logger LOGGER = LogManager.getLogger(DBConnector.class);

    ResourceBundle resource = ResourceBundle.getBundle("database");
    private final String dbUrl = resource.getString("db.url");
    private final String dbUser = resource.getString("db.user");
    private final String dbPassword = resource.getString("db.password");

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            LOGGER.error("connection error", e);
            return null;
        }
    }

    public void registerDrivers() {
        LOGGER.trace("registering sql drivers");

        try {
            DriverManager.registerDriver(DriverManager.getDriver(dbUrl));
        } catch (SQLException e) {
            LOGGER.error("could not register drivers", e);
            throw new IllegalStateException("unsuccessful db driver registration attempt");
        }
    }

    public void deregisterDrivers() {
        LOGGER.trace("unregistering sql drivers");

        final Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error("could not deregister driver", e);
            }
        }
    }
}
