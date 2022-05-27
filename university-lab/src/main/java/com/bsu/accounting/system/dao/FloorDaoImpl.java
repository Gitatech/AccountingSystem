package com.bsu.accounting.system.dao;

import com.bsu.accounting.system.exception.FloorNotFoundException;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;


public class FloorDaoImpl implements AbstractDAO<Floor, Number> {

    private static final Logger LOGGER = LogManager.getLogger(FloorDaoImpl.class);

    private static final String NOT_FOUND_FLOOR_MSG = "did not found floor";
    private static final String COULD_NOT_FIND_THE_FLOOR_WITH_GIVEN_ID = "could not find the floor with given id";

    private static final String FLOOR_NUMBER_COLUMN = "f_number";
    private static final String HEIGHT_COLUMN = "height";
    private static final String LENGTH_COLUMN = "length";
    private static final String WIDTH_COLUMN = "width";

    private static final String FIND_ALL_FLOORS_FROM_DB_SQL =
            "select f.id as id, f.f_number as f_number, p.f_height as height," +
                    " p.f_length as length, p.f_width as width, p.f_height as height" +
                    " from acc_system_floor f join f_params p on f.id = p.floor_id;";

    private static final String FIND_FLOOR_BY_ID_FROM_DB_SQL =
            "select f.id as id, f.f_number as f_number, p.f_height as height," +
                    " p.f_length as length, p.f_width as width, p.f_height as height" +
                    " from acc_system_floor f join f_params p on f.id = p.floor_id" +
                    " where f.id = ?;";

    private static final String DELETE_FLOOR_BY_ID_FROM_DB =
            "delete from acc_system_floor f where f.id = ?;";

    private static final String INSERT_FLOOR_INTO_DB =
            "insert into acc_system_floor(f_number, house_id)" +
                    " values (?,?);";

    private static final String INSERT_FLOOR_PARAMS_INTO_DB =
            "insert into f_params(f_height, f_length, f_width, floor_id)" +
                    " values (?,?,?,?);";

    private final Connection connection;

    public FloorDaoImpl() {
        connection = ConnectorDB.getConnection();
    }

    @Override
    public List<Floor> findAll() {
        if (connection != null) {
            try (final Statement statement = connection.createStatement();
                 final ResultSet resultSet = statement.executeQuery(FIND_ALL_FLOORS_FROM_DB_SQL)) {
                List<Floor> floors = new ArrayList<>();
                while (resultSet.next()) {
                    Floor floor = extractFloor(resultSet).orElseThrow(
                            () -> new FloorNotFoundException(NO_FOUND_MSG)
                    );
                    floors.add(floor);
                }
                return floors;
            } catch (SQLException e) {
                LOGGER.error(SQL_ERROR_MSG, e);
            } catch (FloorNotFoundException e) {
                LOGGER.error(NOT_FOUND_FLOOR_MSG, e);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return Collections.emptyList();
    }

    @Override
    public Floor findEntityById(Number id) {
        if (connection != null) {
            try (final PreparedStatement preparedStatement =
                         connection.prepareStatement(FIND_FLOOR_BY_ID_FROM_DB_SQL)) {
                preparedStatement.setInt(1, (int) id);
                final ResultSet resultSet = preparedStatement.executeQuery();
                Floor floor = new Floor();
                while (resultSet.next()) {
                    floor = extractFloor(resultSet).orElseThrow(
                            () -> new FloorNotFoundException(NO_FOUND_MSG)
                    );
                }
                return floor;
            } catch (SQLException e) {
                LOGGER.error(SQL_ERROR_MSG, e);
            } catch (FloorNotFoundException e) {
                LOGGER.error(NOT_FOUND_FLOOR_MSG, e);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return null;
    }

    @Override
    public boolean delete(Number id) {
        AbstractDAO<Apartment, Number> abstractDAO = new ApartmentDaoImpl();
        return abstractDAO.delete(id, DELETE_FLOOR_BY_ID_FROM_DB);
    }

    @Override
    public boolean create(Floor entity, Number houseId) {
        if (connection != null) {
            if (insertFloorIntoDB(entity, (int) houseId)) {
                return insertFloorParamsIntoDB(entity);
            }
        } else {
            LOGGER.error(DB_CONNECTION_ERROR);
        }
        return false;
    }

    @Override
    public Floor update(Floor entity) {
        Floor floor = findEntityById(entity.getFloorId());
        if (floor != null) {
            if (connection != null) {
                try (final PreparedStatement preparedStatement =
                             connection.prepareStatement(FIND_FLOOR_BY_ID_FROM_DB_SQL,
                                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    preparedStatement.setInt(1, (int) entity.getFloorId());
                    final ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        resultSet.updateInt(FLOOR_NUMBER_COLUMN, (int) entity.getFloorId());
                        resultSet.updateDouble(HEIGHT_COLUMN, entity.getFloorHeight());
                        resultSet.updateDouble(LENGTH_COLUMN, entity.getFloorLength());
                        resultSet.updateDouble(WIDTH_COLUMN, entity.getFloorWidth());
                        resultSet.updateRow();
                    }
                    resultSet.beforeFirst();
                    floor = extractFloor(resultSet).orElseThrow(
                            () -> new FloorNotFoundException(NO_FOUND_MSG)
                    );
                    return floor;
                } catch (SQLException e) {
                    LOGGER.error(SQL_ERROR_MSG);
                } catch (FloorNotFoundException e) {
                    LOGGER.error(NOT_FOUND_FLOOR_MSG, e);
                }
            }
        } else {
            throw new NoSuchElementException(COULD_NOT_FIND_THE_FLOOR_WITH_GIVEN_ID);
        }
        return null;
    }

    private boolean insertFloorParamsIntoDB(Floor entity) {
        try (final PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_FLOOR_PARAMS_INTO_DB)) {
            preparedStatement.setInt(1, (int) entity.getFloorHeight());
            preparedStatement.setDouble(2, entity.getFloorLength());
            preparedStatement.setDouble(3, entity.getFloorWidth());
            preparedStatement.setInt(4, (int) entity.getFloorId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(SQL_ERROR_MSG);
        }
        return false;
    }

    private boolean insertFloorIntoDB(Floor entity, int houseId) {
        try (final PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_FLOOR_INTO_DB)) {
            preparedStatement.setInt(1, Integer.parseInt(String.valueOf(entity.getFloorId())));
            preparedStatement.setInt(2, houseId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(SQL_ERROR_MSG);
        }
        return false;
    }

    private Optional<Floor> extractFloor(ResultSet resultSet) {
        try {
            return Optional.of(new Floor(
                    resultSet.getInt(FLOOR_NUMBER_COLUMN),
                    resultSet.getDouble(HEIGHT_COLUMN),
                    resultSet.getDouble(LENGTH_COLUMN),
                    resultSet.getDouble(WIDTH_COLUMN)
            ));
        } catch (SQLException e) {
            LOGGER.error(COULD_NOT_EXTRACT_VALUE_MSG, e);
            return Optional.empty();
        }
    }
}
