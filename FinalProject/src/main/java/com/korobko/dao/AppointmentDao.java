package com.korobko.dao;

import com.korobko.entities.*;
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
     * todo
     * @return list of poor Appointment objects
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
            closeResources(connectionWrapper, preparedStatement, null);
        }
        return result;
    }

    public int deleteAppointment(Long appointmentId) {
        String sql = ResourceManager.QUERIES.getProperty(DELETE_APPOINTMENT_BY_ID);
        return executeUpdate(appointmentId, sql);
    }

    public int updateAppointment(Long appointmentId, String busId) {
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_APPOINTMENT);
        return executeUpdate(appointmentId, busId, sql);
    }

    public int approveAppointment(Long appointmentId) {
        String sql = ResourceManager.QUERIES.getProperty(APPROVE_APPOINTMENT);
        return executeUpdate(appointmentId, sql);
    }
}
