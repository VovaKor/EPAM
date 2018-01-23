package com.korobko.dao;

import com.korobko.entities.Employee;
import com.korobko.entities.EmployeePosition;
import com.korobko.entities.Names;
import com.korobko.utils.ResourceManager;
import com.korobko.utils.connection.ConnectionWrapper;
import com.korobko.utils.connection.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.korobko.dao.DBColumns.*;

/**
 * @author Vova Korobko
 */
public class EmployeeDao implements Dao {

    private static final String SELECT_EMPLOYEE_BY_EMAIL = "select.employee.by.email";
    private static final String SELECT_SUBORDINATES = "select.subordinates";
    private static final String UPDATE_POSITION = "update.position";
    private static final String INSERT_EMPLOYEE = "insert.employee";
    private static final String SELECT_EMPLOYEE_BY_ID = "select.employee.by.id";
    private static final String UPDATE_EMPLOYEE_NO_PASS = "update.employee.no.pass";
    private static final String UPDATE_EMPLOYEE_AND_PASS = "update.employee.and.pass";

    EmployeeDao() {
    }

    public Employee findEmployeeByEmail(String login) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_EMPLOYEE_BY_EMAIL);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmail(login);
                employee.setEmployeeId(resultSet.getLong(EMPLOYEE_ID));
                employee.setPassword(resultSet.getString(PASSWORD));
                String role = resultSet.getString(ROLE);
                if (Objects.nonNull(role)) {
                    employee.setPosition(EmployeePosition.valueOf(role));
                }
                Names names = new Names();
                names.setFirstName(resultSet.getString(FIRST_NAME));
                names.setLastName(resultSet.getString(LAST_NAME));
                names.setPatronymic(resultSet.getString(PATRONYMIC));
                employee.setNames(names);
            }
        } catch (SQLException e) {
            logger.error("Exception while finding employee by email", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, resultSet);
        }
        return employee;
    }

    /**
     * Fetches {@code Employee} objects with all {@code EmployeePosition}
     * including {@code Employee} objects without any {@code EmployeePosition}
     * assigned except {@code EmployeePosition.DIRECTOR} sorted by
     * {@code EmployeePosition} in descending order
     *
     * @return the Java {@code List} of {@code Employee} objects
     */
    public List<Employee> getSubordinates() {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Employee> employees = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_SUBORDINATES);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getLong(EMPLOYEE_ID));
                String role = resultSet.getString(ROLE);
                if (Objects.nonNull(role)) {
                    employee.setPosition(EmployeePosition.valueOf(role));
                }
                Names names = new Names();
                names.setFirstName(resultSet.getString(FIRST_NAME));
                names.setLastName(resultSet.getString(LAST_NAME));
                names.setPatronymic(resultSet.getString(PATRONYMIC));
                employee.setNames(names);
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.error("Exception while getting subordinates", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, resultSet);
        }
        return employees;
    }

    /**
     * Updates {@code Employee} in database {@code employees} table. If given {@code Integer}
     * parameter is 0 then sets column {@code role_id} to SQL {@code NULL}
     *
     * @param empId the Java {@code Long} value {@code Employee} id
     * @param positionId the Java {@code Integer} value {@code EmployeePosition} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int updatePosition(Long empId, Integer positionId) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_POSITION);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            if (Objects.nonNull(positionId)) {
                preparedStatement.setInt(1, positionId);
            } else {
                preparedStatement.setNull(1, Types.NULL);
            }
            preparedStatement.setLong(2, empId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception renewal employee position", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }

    public int insertEmployee(String email, String password) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(INSERT_EMPLOYEE);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while inserting employee", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }

    public Employee findEmployeeById(Long employeeId) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_EMPLOYEE_BY_ID);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setLong(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmployeeId(employeeId);
                employee.setEmail(resultSet.getString(EMAIL));
                employee.setPassword(resultSet.getString(PASSWORD));
                String role = resultSet.getString(ROLE);
                if (Objects.nonNull(role)) {
                    employee.setPosition(EmployeePosition.valueOf(role));
                }
                Names names = new Names();
                names.setFirstName(resultSet.getString(FIRST_NAME));
                names.setLastName(resultSet.getString(LAST_NAME));
                names.setPatronymic(resultSet.getString(PATRONYMIC));
                employee.setNames(names);
            }
        } catch (SQLException e) {
            logger.error("Exception while finding employee by id", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, resultSet);
        }
        return employee;
    }

    public int updateEmployee(Employee employee) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_EMPLOYEE_NO_PASS);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, employee.getEmail());
            Names names = employee.getNames();
            preparedStatement.setString(2, names.getFirstName());
            preparedStatement.setString(3, names.getPatronymic());
            preparedStatement.setString(4, names.getLastName());
            preparedStatement.setLong(5, employee.getEmployeeId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception renewal employee", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }

    /**
     * Updates all columns in database {@code employees} and {@code names}
     * tables and sets {@param newPassword} instead of existed password.
     *
     * @param newPassword the {@code String} value new {@code Employee}
     *                    password to insert
     * @param employee the {@code Employee} object to update
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int updateEmployee(String newPassword, Employee employee) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_EMPLOYEE_AND_PASS);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setString(2, newPassword);
            Names names = employee.getNames();
            preparedStatement.setString(3, names.getFirstName());
            preparedStatement.setString(4, names.getPatronymic());
            preparedStatement.setString(5, names.getLastName());
            preparedStatement.setLong(6, employee.getEmployeeId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception renewal employee with new password", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }
}
