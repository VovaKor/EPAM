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
import com.korobko.utils.InputValidator;
import com.korobko.utils.connection.TransactionManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public enum EmployeeService {
    INSTANCE;

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

    public int updatePosition(String employeeId, String roleId) {
        Long empId = Long.valueOf(employeeId);
        Integer positionId = Integer.valueOf(roleId);
        if (positionId == 0) {
            positionId = null;
        }
        return employeeDao.updatePosition(empId, positionId);
    }

    public int register(String email, String password, String firstName, String patronymic, String lastName) {
        int result = 0;
        if (InputValidator.isNullOrEmpty(email, password, firstName, lastName)
                || !Authentication.isCredentialsValid(email, password)) {
            return result;
        }
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
            //todo
            try {
                TransactionManager.rollbackTransaction();
            } catch (TransactionException | SQLException e1) {
                //todo
            }
        }
        return result;
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeDao.findEmployeeById(employeeId);
    }

    public int updateEmployee(String newPassword, Employee newEmployee) {
        int result = 0;
        if (InputValidator.isNullOrEmpty(newEmployee.getEmail(), newEmployee.getPassword(), newEmployee.getNames().getFirstName(), newEmployee.getNames().getLastName())
                || !Authentication.isCredentialsValid(newEmployee.getEmail(), newEmployee.getPassword())) {
            return result;
        }
        try {
            TransactionManager.beginTransaction();
            Employee oldEmployee = employeeDao.findEmployeeById(newEmployee.getEmployeeId());

            if (!oldEmployee.getEmail().equals(newEmployee.getEmail()) && Objects.nonNull(employeeDao.findEmployeeByEmail(newEmployee.getEmail()))) {
                TransactionManager.endTransaction();
                return Constants.ERROR_CODE;
            }
            String newEmployeePasswordHash = HashGenerator.generateSHA512(newEmployee.getPassword());
            if (Authentication.isPasswordsMatches(oldEmployee.getPassword(), newEmployeePasswordHash)) {
                if (InputValidator.isNullOrEmpty(newPassword)) {
                    result = employeeDao.updateEmployee(newEmployee);
                }
                if (Authentication.isPasswordValid(newPassword)) {
                    result = employeeDao.updateEmployee(HashGenerator.generateSHA512(newPassword), newEmployee);
                }
            }
            TransactionManager.endTransaction();
        } catch (TransactionException | SQLException e) {
            //todo
            try {
                TransactionManager.rollbackTransaction();
            } catch (TransactionException | SQLException e1) {
                //todo
            }
        }
        return result;
    }
}
