package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.entities.Employee;
import com.korobko.entities.EmployeePosition;
import com.korobko.entities.Names;
import com.korobko.services.EmployeeService;
import com.korobko.utils.HashGenerator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Objects;

import static com.korobko.dao.DBColumns.*;
import static com.korobko.utils.Constants.*;


/**
 * @author Vova Korobko
 */
public class UpdateProfileCommand implements Command {

    private static final String PARR_NAME_OLD_PASSWORD = "old_password";
    private static final String PARR_NAME_NEW_PASSWORD = "new_password";
    private static final String MESSAGE_ERROR_UPDATE_PROFILE = "message.error.update.profile";
    private static final String MESSAGE_PROFILE_UPDATED = "message.profile.updated";
    private static final int ROWS_AFFECTED_ON_UPDATE = 2;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String newPassword = request.getParameter(PARR_NAME_NEW_PASSWORD);
        Employee employee = createEmployee(session, request);

        int result = EmployeeService.INSTANCE.updateEmployee(newPassword, employee);
        if (result == ROWS_AFFECTED_ON_UPDATE) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_PROFILE_UPDATED));

            String role = (String) session.getAttribute(DBColumns.ROLE);
            if (Objects.nonNull(role)) {
                switch (EmployeePosition.valueOf(role)) {
                    case ADMIN:
                        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_FEEDBACK);
                    case DRIVER:
                        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DRIVER_FEEDBACK);
                    case DIRECTOR:
                        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);
                }
            }
        }
        if (result == ERROR_CODE) {
            request.setAttribute(ATTR_ERROR_LOGIN_PASS_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_EMAIL_EXIST));
        } else {
            request.setAttribute(ATTR_ERROR_LOGIN_PASS_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_PROFILE));
        }
        return CommandEnum.PROFILE.getCurrentCommand().execute(request);
    }

    private Employee createEmployee(HttpSession session, HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String oldPassword = request.getParameter(PARR_NAME_OLD_PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String patronymic = request.getParameter(PATRONYMIC);
        String lastName = request.getParameter(LAST_NAME);
        Employee employee = new Employee();
        employee.setEmployeeId((Long) session.getAttribute(EMPLOYEE_ID));
        employee.setEmail(email);
        employee.setPassword(oldPassword);
        Names names = new Names();
        names.setFirstName(firstName);
        names.setPatronymic(patronymic);
        names.setLastName(lastName);
        employee.setNames(names);
        return employee;
    }
}
