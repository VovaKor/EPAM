package com.korobko.dao;

import com.korobko.entities.Appointment;
import com.korobko.entities.Bus;
import com.korobko.entities.Employee;
import com.korobko.entities.Names;
import com.korobko.utils.DateTime;
import com.korobko.utils.ResourceManager;
import com.korobko.utils.connection.ConnectionWrapper;
import com.korobko.utils.connection.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.korobko.dao.DBColumns.*;

/**
 * An object used to perform CRUD operations with {@code Appointment} entities.
 *
 * @author Vova Korobko
 */
public class AppointmentDao implements Dao {

    private static final String SELECT_APPOINTMENTS_AND_DRIVERS = "select.appointments.and.drivers";
    private static final String SELECT_POOR_APPOINTMENT_BY_ID = "select.appointment.poor.by.id";
    private static final String SELECT_POOR_APPOINTMENT_BY_EMPLOYEE_ID = "select.appointment.poor.by.employee.id";
    private static final String INSERT_APPOINTMENT = "insert.appointment";
    private static final String DELETE_APPOINTMENT_BY_ID = "delete.appointment.by.id";
    private static final String UPDATE_APPOINTMENT = "update.appointment";
    private static final String APPROVE_APPOINTMENT = "approve.appointment";

    AppointmentDao() {
    }

    /**
     * Lazy fetches {@code Appointment} objects with {@code EmployeePosition.DRIVER}
     * sorted in descending order by approved date and then by id
     *
     * @return list of poor {@code Appointment} objects
     */
    public List<Appointment> getAppointmentsIncludingFreeDrivers() {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Appointment> appointments = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_APPOINTMENTS_AND_DRIVERS);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            appointments = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getLong(EMPLOYEE_ID));
                Names names = new Names();
                names.setFirstName(resultSet.getString(FIRST_NAME));
                names.setLastName(resultSet.getString(LAST_NAME));
                names.setPatronymic(resultSet.getString(PATRONYMIC));
                employee.setNames(names);
                Bus bus = new Bus();
                bus.setVIN(resultSet.getString(BUS_ID));
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getLong(APPOINTMENT_ID));
                appointment.setBus(bus);
                appointment.setEmployee(employee);
                appointment.setCreated(DateTime.timestampToLocalDateTime(resultSet.getTimestamp(CREATED_ON)));
                appointment.setApproved(DateTime.timestampToLocalDateTime(resultSet.getTimestamp(APPROVED_ON)));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            logger.error("Exception while getting appointments with free drivers", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, resultSet);
        }
        return appointments;
    }

    /**
     * Lazy fetches {@code Appointment} object by id. Don't fetch {@code Names}.
     * {@code Employee} and {@code Bus} objects only will have their ids.
     *
     * @param appointmentId the Java {@code Long} value
     * @return {@code Appointment} object or {@code null} if no {@code Appointment}
     *         with given id
     */
    public Appointment getPoorAppointmentById(Long appointmentId) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Appointment appointment = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_POOR_APPOINTMENT_BY_ID);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setLong(1, appointmentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getLong(EMPLOYEE_ID));
                Bus bus = new Bus();
                bus.setVIN(resultSet.getString(BUS_ID));
                appointment = new Appointment();
                appointment.setAppointmentId(appointmentId);
                appointment.setBus(bus);
                appointment.setEmployee(employee);
                appointment.setCreated(DateTime.timestampToLocalDateTime(resultSet.getTimestamp(CREATED_ON)));
                appointment.setApproved(DateTime.timestampToLocalDateTime(resultSet.getTimestamp(APPROVED_ON)));
            }
        } catch (SQLException e) {
            logger.error("Exception while getting appointment by id", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, resultSet);
        }
        return appointment;
    }

    /**
     * Lazy fetches {@code Appointment} object by {@code Employee} id
     *
     * @param employeeId the Java {@code Long} value
     * @return {@code Appointment} object or {@code null} if no {@code Appointment}
     *         with given {@code Employee} id
     */
    public Appointment getPoorAppointmentByEmployeeId(Long employeeId) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Appointment appointment = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_POOR_APPOINTMENT_BY_EMPLOYEE_ID);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setLong(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                Bus bus = new Bus();
                bus.setVIN(resultSet.getString(BUS_ID));
                appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getLong(APPOINTMENT_ID));
                appointment.setBus(bus);
                appointment.setEmployee(employee);
                appointment.setCreated(DateTime.timestampToLocalDateTime(resultSet.getTimestamp(CREATED_ON)));
                appointment.setApproved(DateTime.timestampToLocalDateTime(resultSet.getTimestamp(APPROVED_ON)));
            }
        } catch (SQLException e) {
            logger.error("Exception while getting appointment by employee id", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, resultSet);
        }
        return appointment;
    }

    /**
     * Creates new row in database {@code appointments} table and inserts data
     *
     * @param employeeId the Java {@code Long} value {@code Employee} id
     * @param busId the Java {@code String} value {@code Bus} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int insertAppointment(Long employeeId, String busId) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(INSERT_APPOINTMENT);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setString(2, busId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while inserting appointment", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }

    /**
     * Deletes row from database {@code appointments} table with given
     * {@code Appointment} id
     *
     * @param appointmentId the Java {@code Long} value {@code Appointment} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int deleteAppointment(Long appointmentId) {
        String sql = ResourceManager.QUERIES.getProperty(DELETE_APPOINTMENT_BY_ID);
        return executeUpdate(appointmentId, sql);
    }

    /**
     * Updates row in database {@code appointments} table sets given {@code Bus} id
     *
     * @param appointmentId the Java {@code Long} value {@code Appointment} id
     *                      to update
     * @param busId the Java {@code String} value {@code Bus} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int updateAppointment(Long appointmentId, String busId) {
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_APPOINTMENT);
        return executeUpdate(appointmentId, busId, sql);
    }

    /**
     * Updates row in database {@code appointments} table sets {@code approved_on}
     * column to {@code CURRENT_TIMESTAMP}
     *
     * @param appointmentId the Java {@code Long} value {@code Appointment} id
     *                      to update
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int approveAppointment(Long appointmentId) {
        String sql = ResourceManager.QUERIES.getProperty(APPROVE_APPOINTMENT);
        return executeUpdate(appointmentId, sql);
    }
}
