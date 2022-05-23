package com.bsu.accounting.system.dao;

import com.bsu.accounting.system.exception.ApartmentNotFoundException;
import com.bsu.accounting.system.model.Apartment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class ApartmentDaoImpl implements AbstractDAO<Apartment, Number> {

    private static final Logger LOGGER = LogManager.getLogger(ApartmentDaoImpl.class);

    private static final String NOT_FOUND_APARTMENTS_MSG = "did not found apartments";
    private static final String COULD_NOT_FIND_THE_APARTMENT_WITH_GIVEN_ID = "could not find the apartment with given id";

    private static final String ID_COLUMN = "id";
    private static final String RESIDENTS_COLUMN = "ap_residents";
    private static final String LENGTH_COLUMN = "ap_length";
    private static final String WIDTH_COLUMN = "ap_width";

    private static final String FIND_ALL_APARTMENTS_FROM_DB_SQL =
            " select ap.id as id, a.ap_residents as ap_residents," +
                    " a.ap_length as ap_length, a.ap_width as ap_width, ap.floor_id as f_id" +
                    " from acc_system_apartment ap join ap_params a on ap.id = a.ap_id;";

    private static final String FIND_APARTMENT_BY_ID_FROM_DB_SQL =
            " select ap.id as id, a.ap_residents as ap_residents," +
                    " a.ap_length as ap_length, a.ap_width as ap_width, ap.floor_id as f_id" +
                    " from acc_system_apartment ap join ap_params a on ap.id = a.ap_id" +
                    " where ap.id = ?;";

    private static final String DELETE_APARTMENT_BY_ID_FROM_DB =
            "delete from acc_system_apartment ap where ap.id = ?";

    private static final String INSERT_APARTMENTS_INTO_DB =
            "insert into acc_system_apartment (id, floor_id)" +
                    "values (?,?);" +
                    "insert into ap_params (ap_residents, ap_length, ap_width, ap_id)" +
                    "values (?,?,?,?);";


    @Override
    public List<Apartment> findAll() {
        Connection connection = ConnectorDB.getConnection();
        if (connection != null) {
            try (final Statement statement = connection.createStatement();
                 final ResultSet resultSet = statement.executeQuery(FIND_ALL_APARTMENTS_FROM_DB_SQL)) {
                List<Apartment> apartments = new ArrayList<>();
                while (resultSet.next()) {
                    Apartment apartment = extractApartment(resultSet).orElseThrow(
                            () -> new ApartmentNotFoundException(NO_FOUND_MSG)
                    );
                    apartments.add(apartment);
                }
                return apartments;
            } catch (SQLException e) {
                LOGGER.error(SQL_ERROR_MSG, e);
            } catch (ApartmentNotFoundException e) {
                LOGGER.error(NOT_FOUND_APARTMENTS_MSG, e);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return Collections.emptyList();
    }

    @Override
    public Apartment findEntityById(Number id) {
        final Connection connection = ConnectorDB.getConnection();
        if (connection != null) {
            try (final PreparedStatement preparedStatement =
                         connection.prepareStatement(FIND_APARTMENT_BY_ID_FROM_DB_SQL)) {
                preparedStatement.setInt(1, (int) id);
                final ResultSet resultSet = preparedStatement.executeQuery();
                Apartment apartment = new Apartment();
                while (resultSet.next()) {
                    apartment = extractApartment(resultSet).orElseThrow(
                            () -> new ApartmentNotFoundException(NO_FOUND_MSG)
                    );
                }
                return apartment;
            } catch (SQLException e) {
                LOGGER.error(SQL_ERROR_MSG, e);
            } catch (ApartmentNotFoundException e) {
                LOGGER.error(NOT_FOUND_APARTMENTS_MSG, e);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return null;
    }

    @Override
    public boolean delete(Number id) {
        AbstractDAO<Apartment, Number> abstractDAO = new ApartmentDaoImpl();
        return abstractDAO.delete(id, DELETE_APARTMENT_BY_ID_FROM_DB);
    }

    @Override
    public boolean create(Apartment entity, Number numberOfFloor) {
        final Connection connection = ConnectorDB.getConnection();
        if (connection != null) {
            try (final PreparedStatement preparedStatement =
                         connection.prepareStatement(INSERT_APARTMENTS_INTO_DB)) {
                preparedStatement.setInt(1, Integer.parseInt(String.valueOf(entity.getId())));
                preparedStatement.setInt(2, (int) numberOfFloor);
                preparedStatement.setInt(3, entity.getNumberOfResidents());
                preparedStatement.setDouble(4, entity.getTotalApartmentLength());
                preparedStatement.setDouble(5, entity.getTotalApartmentLength());
                preparedStatement.setInt(6, entity.getId());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                LOGGER.error(SQL_ERROR_MSG);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return false;
    }

    @Override
    public Apartment update(Apartment entity) {
        Apartment apartment = findEntityById(entity.getId());
        if (apartment != null) {
            final Connection connection = ConnectorDB.getConnection();
            if (connection != null) {
                try (final PreparedStatement preparedStatement =
                             connection.prepareStatement(FIND_APARTMENT_BY_ID_FROM_DB_SQL,
                                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    preparedStatement.setInt(1, entity.getId());
                    final ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        resultSet.updateInt(ID_COLUMN, entity.getId());
                        resultSet.updateInt(RESIDENTS_COLUMN, entity.getNumberOfResidents());
                        resultSet.updateDouble(LENGTH_COLUMN, entity.getTotalApartmentLength());
                        resultSet.updateDouble(WIDTH_COLUMN, entity.getTotalApartmentWidth());
                        resultSet.updateRow();
                    }
                    resultSet.beforeFirst();
                    apartment = extractApartment(resultSet).orElseThrow(
                            () -> new ApartmentNotFoundException(NO_FOUND_MSG)
                    );
                    return apartment;
                } catch (SQLException e) {
                    LOGGER.error(SQL_ERROR_MSG);
                } catch (ApartmentNotFoundException e) {
                    LOGGER.error(NOT_FOUND_APARTMENTS_MSG, e);
                }
            }
        } else {
            throw new NoSuchElementException(COULD_NOT_FIND_THE_APARTMENT_WITH_GIVEN_ID);
        }
        return null;
    }

    private Optional<Apartment> extractApartment(ResultSet resultSet) {
        try {
            return Optional.of(new Apartment(
                    resultSet.getInt(ID_COLUMN),
                    resultSet.getInt(RESIDENTS_COLUMN),
                    resultSet.getDouble(LENGTH_COLUMN),
                    resultSet.getDouble(WIDTH_COLUMN)
            ));
        } catch (SQLException e) {
            LOGGER.error(COULD_NOT_EXTRACT_VALUE_MSG, e);
            return Optional.empty();
        }
    }
}
