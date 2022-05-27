package com.bsu.accounting.system.dao;

import com.bsu.accounting.system.exception.HouseNotFoundException;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.House;
import com.bsu.accounting.system.model.HouseName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class HouseDaoImpl implements AbstractDAO<House, Number> {

    private static final Logger LOGGER = LogManager.getLogger(HouseDaoImpl.class);

    private static final String NOT_FOUND_HOUSE_MSG = "did not found house";
    private static final String COULD_NOT_FIND_THE_HOUSE_WITH_GIVEN_ID = "could not find the house with given id";

    private static final String STREET_NAME_COLUMN = "street_name";
    private static final String HOUSE_NUMBER_COLUMN = "number";
    private static final String LENGTH_COLUMN = "length";
    private static final String WIDTH_COLUMN = "width";
    private static final String HEIGHT_COLUMN = "height";

    private static final String FIND_ALL_HOUSES_FROM_DB_SQL =
            "select h.id as id, h.street as street_name, h.h_number as number," +
                    " hp.h_length as length, hp.h_width as width, hp.h_height as height" +
                    " from acc_system_house h join h_params hp on h.id = hp.house_id;";

    private static final String FIND_HOUSE_BY_ID_FROM_DB_SQL =
            "select h.id as id, h.street as street_name, h.h_number as number, hp.h_length as length," +
                    " hp.h_width as width, hp.h_height as height" +
                    " from acc_system_house h join h_params hp on h.id = hp.house_id" +
                    " where h.id = ?;";

    private static final String DELETE_HOUSE_BY_ID_FROM_DB =
            "delete from acc_system_house h where h.id = ?;";

    private static final String INSERT_HOUSE_INTO_DB =
            "insert into acc_system_house (id, street, h_number) values (?,?,?);";

    private static final String INSERT_HOUSE_PARAMS_INTO_DB =
            "insert into h_params (h_length, h_width, h_height, house_id) values (?,?,?,?);";

    private final Connection connection;

    public HouseDaoImpl() {
        this.connection = ConnectorDB.getConnection();
    }

    @Override
    public List<House> findAll() {
        if (connection != null) {
            try (final Statement statement = connection.createStatement();
                 final ResultSet resultSet = statement.executeQuery(FIND_ALL_HOUSES_FROM_DB_SQL)) {
                List<House> houses = new ArrayList<>();
                while (resultSet.next()) {
                    House house = extractHouse(resultSet).orElseThrow(
                            () -> new HouseNotFoundException(NO_FOUND_MSG)
                    );
                    houses.add(house);
                }
                return houses;
            } catch (SQLException e) {
                LOGGER.error(SQL_ERROR_MSG, e);
            } catch (HouseNotFoundException e) {
                LOGGER.error(NOT_FOUND_HOUSE_MSG, e);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return Collections.emptyList();
    }

    @Override
    public House findEntityById(Number id) {
        if (connection != null) {
            try (final PreparedStatement preparedStatement =
                         connection.prepareStatement(FIND_HOUSE_BY_ID_FROM_DB_SQL)) {
                preparedStatement.setInt(1, (int) id);
                final ResultSet resultSet = preparedStatement.executeQuery();
                House house = new House();
                while (resultSet.next()) {
                    house = extractHouse(resultSet).orElseThrow(
                            () -> new HouseNotFoundException(NO_FOUND_MSG)
                    );
                }
                return house;
            } catch (SQLException e) {
                LOGGER.error(SQL_ERROR_MSG, e);
            } catch (HouseNotFoundException e) {
                LOGGER.error(NOT_FOUND_HOUSE_MSG, e);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return null;
    }

    @Override
    public boolean delete(Number id) {
        AbstractDAO<Apartment, Number> abstractDAO = new ApartmentDaoImpl();
        return abstractDAO.delete(id, DELETE_HOUSE_BY_ID_FROM_DB);
    }

    @Override
    public boolean create(House entity, Number id) {
        if (connection != null) {
            if (insertHouseIntoDB(entity)) {
                return insertHouseParamsIntoDB(entity);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return false;
    }

    @Override
    public House update(House entity) {
        House house = findEntityById(entity.getHouseId());
        if (house != null) {
            if (connection != null) {
                try (final PreparedStatement preparedStatement =
                             connection.prepareStatement(FIND_HOUSE_BY_ID_FROM_DB_SQL,
                                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    preparedStatement.setInt(1, (int) entity.getHouseId());
                    final ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        resultSet.updateString(STREET_NAME_COLUMN, entity.getName().getStreet());
                        resultSet.updateString(HOUSE_NUMBER_COLUMN, entity.getName().getHouseNumber());
                        resultSet.updateDouble(LENGTH_COLUMN, entity.getLength());
                        resultSet.updateDouble(WIDTH_COLUMN, entity.getWidth());
                        resultSet.updateDouble(HEIGHT_COLUMN, entity.getHeight());
                        resultSet.updateRow();
                    }
                    resultSet.beforeFirst();
                    house = extractHouse(resultSet).orElseThrow(
                            () -> new HouseNotFoundException(NO_FOUND_MSG)
                    );
                    return house;
                } catch (SQLException e) {
                    LOGGER.error(SQL_ERROR_MSG);
                } catch (HouseNotFoundException e) {
                    LOGGER.error(NOT_FOUND_HOUSE_MSG, e);
                }
            }
        } else {
            throw new NoSuchElementException(COULD_NOT_FIND_THE_HOUSE_WITH_GIVEN_ID);
        }
        return null;
    }

    private boolean insertHouseIntoDB(House entity) {
        try (final PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_HOUSE_INTO_DB, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setInt(1, (int) entity.getHouseId());
            preparedStatement.setString(2, entity.getName().getStreet());
            preparedStatement.setString(3, entity.getName().getHouseNumber());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    private boolean insertHouseParamsIntoDB(House entity) {
        try (final PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_HOUSE_PARAMS_INTO_DB, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setDouble(1, entity.getLength());
            preparedStatement.setDouble(2, entity.getWidth());
            preparedStatement.setDouble(3, entity.getHeight());
            preparedStatement.setInt(4, (int) entity.getHouseId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    private Optional<House> extractHouse(ResultSet resultSet) {
        try {
            return Optional.of(new House(
                    new HouseName(
                            resultSet.getString(STREET_NAME_COLUMN),
                            resultSet.getString(HOUSE_NUMBER_COLUMN)
                    ),
                    resultSet.getDouble(LENGTH_COLUMN),
                    resultSet.getDouble(WIDTH_COLUMN),
                    resultSet.getDouble(HEIGHT_COLUMN)
            ));
        } catch (SQLException e) {
            LOGGER.error(COULD_NOT_EXTRACT_VALUE_MSG, e);
            return Optional.empty();
        }
    }
}
