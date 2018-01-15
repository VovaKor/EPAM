package com.korobko.services;

import com.korobko.dao.*;
import com.korobko.entities.Appointment;
import com.korobko.entities.Bus;
import com.korobko.entities.Employee;
import com.korobko.entities.Names;
import com.korobko.exceptions.TransactionException;
import com.korobko.utils.connection.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static com.korobko.utils.Constants.*;
/**
 * @author Vova Korobko
 */
public enum AppointmentService {
    INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AppointmentDao appointmentDao;
    private final NamesDao namesDao;
    private final BusDao busDao;

    AppointmentService() {
        this.appointmentDao = (AppointmentDao) DaoFactory.INSTANCE.getDao(DaoType.APPOINTMENT);
        this.namesDao = (NamesDao) DaoFactory.INSTANCE.getDao(DaoType.NAMES);
        this.busDao = (BusDao) DaoFactory.INSTANCE.getDao(DaoType.BUS);
    }

    public List<Appointment> getAppointmentsIncludingFreeDrivers() {
        return appointmentDao.getAppointmentsIncludingFreeDrivers();
    }

    /**
     * todo
     *
     * @param appointmentId
     * @param employeeId
     * @return
     */
    public Appointment getPoorAppointment(String appointmentId, String employeeId) {
        Long appId = Long.valueOf(appointmentId);
        Long empId = Long.valueOf(employeeId);
        Names names = namesDao.findNamesByEmployeeId(empId);
        Appointment appointment = appointmentDao.getPoorAppointmentById(appId);
        if (Objects.isNull(appointment)) {
            Employee employee = new Employee();
            employee.setEmployeeId(empId);
            employee.setNames(names);
            appointment = new Appointment();
            appointment.setAppointmentId(appId);
            appointment.setEmployee(employee);
        } else {
            appointment.getEmployee().setNames(names);
        }
        return appointment;
    }

    public int executeUpdate(String employeeId, String busId) {
        try {
            int result = 0;
            TransactionManager.beginTransaction();
            Long empId = Long.valueOf(employeeId);
            Appointment appointment = appointmentDao.getPoorAppointmentByEmployeeId(empId);
            if (Objects.isNull(appointment) && busId.isEmpty()) {
                TransactionManager.endTransaction();
                return ROWS_AFFECTED;
            }
            if (Objects.isNull(appointment)) {
                result = appointmentDao.insertAppointment(empId, busId);
                TransactionManager.endTransaction();
                return result;
            }
            if (busId.isEmpty()) {
                result = appointmentDao.deleteAppointment(appointment.getAppointmentId());
                TransactionManager.endTransaction();
                return result;
            }
            if (appointment.getBus().getVIN().equals(busId)) {
                TransactionManager.endTransaction();
                return ROWS_AFFECTED;
            }
            result = appointmentDao.updateAppointment(appointment.getAppointmentId(), busId);
            TransactionManager.endTransaction();
            return result;
        } catch (SQLException | TransactionException e) {
            logger.error("Exception update appointment", e);
            try {
                TransactionManager.rollbackTransaction();
            } catch (TransactionException | SQLException e1) {
                logger.error("Exception rollback appointment", e);
            }
        }
        return ERROR_CODE;
    }

    public Appointment findAppointment(Long employeeId) {
        Appointment appointment = appointmentDao.getPoorAppointmentByEmployeeId(employeeId);
        if (Objects.nonNull(appointment)) {
            Bus bus = busDao.getBusByVIN(appointment.getBus().getVIN());
            appointment.setBus(bus);
            return appointment;
        }
        return null;
    }

    public int approveAppointment(String appId) {
        if (Objects.isNull(appId) || appId.isEmpty()) {
            return ERROR_CODE;
        }
        Long appointmentId = Long.valueOf(appId);
        int result = 0;
        try {
            TransactionManager.beginTransaction();
            Appointment appointment = appointmentDao.getPoorAppointmentById(appointmentId);
            if (Objects.nonNull(appointment.getApproved())) {
                result = ERROR_CODE;
            } else {
                result = appointmentDao.approveAppointment(appointmentId);
            }
            TransactionManager.endTransaction();
        } catch (TransactionException | SQLException e) {
            logger.error("Exception approve appointment", e);
            try {
                TransactionManager.rollbackTransaction();
            } catch (TransactionException | SQLException e1) {
                logger.error("Exception rollback approval", e);
            }
        }
        return result;
    }
}
