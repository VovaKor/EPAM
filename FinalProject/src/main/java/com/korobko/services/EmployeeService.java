package com.korobko.services;

import com.korobko.dao.DaoFactory;
import com.korobko.dao.DaoType;
import com.korobko.dao.EmployeeDao;
import com.korobko.dao.NamesDao;
import com.korobko.entities.Employee;
import com.korobko.exceptions.TransactionException;
import com.korobko.utils.Authentication;
import com.korobko.utils.Constants;
import com.korobko.utils.HashGenerator;
import com.korobko.utils.connection.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public enum EmployeeService {
    INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private EmployeeDao employeeDao;
    private NamesDao namesDao;

    EmployeeService() {
        this.employeeDao = (EmployeeDao) DaoFactory.INSTANCE.getDao(DaoType.EMPLOYEE);
        this.namesDao = (NamesDao) DaoFactory.INSTANCE.getDao(DaoType.NAMES);
    }

    public Employee getEmployeeByEmail(String login) {
        return employeeDao.findEmployeeByEmail(login);
    }

    public List<Employee> getSubordinates() {
        return employeeDao.getSubordinates();
    }

    public int updatePosition(Long employeeId, Integer roleId) {
        return employeeDao.updatePosition(employeeId, roleId);
    }

    public int register(String email, String password, String firstName, String patronymic, String lastName) {
        int result = 0;
        try {
            TransactionManager.beginTransaction();
            if (Objects.nonNull(employeeDao.findEmployeeByEmail(email))) {
                TransactionManager.endTransaction();
                return Constants.ERROR_CODE;
            }
            result = employeeDao.insertEmployee(email, HashGenerator.generateSHA512(password));
            result = namesDao.insertNames(firstName, patronymic, lastName);
            TransactionManager.endTransaction();
        } catch (TransactionException | SQLException e) {
            logger.error("Exception register employee", e);
            try {
                TransactionManager.rollbackTransaction();
            } catch (TransactionException | SQLException e1) {
                logger.error("Exception rollback registration", e);
            }
        }
        return result;
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeDao.findEmployeeById(employeeId);
    }

    public int updateEmployee(String newPassword, Employee newEmployee) {
        int result = 0;
        try {
            TransactionManager.beginTransaction();
            Employee oldEmployee = employeeDao.findEmployeeById(newEmployee.getEmployeeId());

            if (!oldEmployee.getEmail().equals(newEmployee.getEmail()) && Objects.nonNull(employeeDao.findEmployeeByEmail(newEmployee.getEmail()))) {
                TransactionManager.endTransaction();
                return Constants.ERROR_CODE;
            }
            String newEmployeePasswordHash = HashGenerator.generateSHA512(newEmployee.getPassword());
            if (Authentication.isPasswordsMatches(oldEmployee.getPassword(), newEmployeePasswordHash)) {
                if (Objects.isNull(newPassword) || newPassword.isEmpty()) {
                    result = employeeDao.updateEmployee(newEmployee);
                }
                if (Authentication.isPasswordValid(newPassword)) {
                    result = employeeDao.updateEmployee(HashGenerator.generateSHA512(newPassword), newEmployee);
                }
            }
            TransactionManager.endTransaction();
        } catch (TransactionException | SQLException e) {
            logger.error("Exception update employee", e);
            try {
                TransactionManager.rollbackTransaction();
            } catch (TransactionException | SQLException e1) {
                logger.error("Exception rollback employee renewal", e);
            }
        }
        return result;
    }
}
