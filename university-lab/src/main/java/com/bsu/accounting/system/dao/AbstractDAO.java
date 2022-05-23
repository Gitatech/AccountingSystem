package com.bsu.accounting.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<T, K extends Number> {

    String SQL_ERROR_MSG = "sql error occurred working with entity";
    String DB_CONNECTION_ERROR = "database connection error";
    String COULD_NOT_EXTRACT_VALUE_MSG = "could not extract value from result set";
    String NO_FOUND_MSG = "could not extract entity";

    List<T> findAll();

    T findEntityById(K id);

    boolean delete(K id);

    boolean create(T entity, K id);

    T update(T entity);

    default boolean delete(K id, String sql) {
        final Connection connection = ConnectorDB.getConnection();
        if (connection != null) {
            try (final PreparedStatement preparedStatement =
                         connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, (Integer) id);
                return preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                throw new RuntimeException(SQL_ERROR_MSG, e);
            }
        } else {
            throw new RuntimeException(DB_CONNECTION_ERROR);
        }
    }

}
