package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.entities.Employee;
import com.korobko.entities.EmployeePosition;
import com.korobko.services.EmployeeService;
import com.korobko.utils.Authentication;
import com.korobko.utils.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.korobko.dao.DBColumns.PASSWORD;
import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
class LoginCommand implements Command {

    private static final String MESSAGE_LOGIN_ERROR = "message.error.login";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String execute(HttpServletRequest request) {
        String webPageUri = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PASSWORD);
        if (Authentication.isCredentialsValid(login, pass)) {
            Employee employee = EmployeeService.INSTANCE.getEmployeeByEmail(login);
            if (Objects.nonNull(employee)
                    && Objects.nonNull(employee.getPosition())
                    && Authentication.isPasswordsMatches(pass, employee.getPassword())) {
                HttpSession session = request.getSession();
                EmployeePosition position = employee.getPosition();
                session.setAttribute(DBColumns.ROLE, position.name());
                session.setAttribute(DBColumns.EMPLOYEE_ID, employee.getEmployeeId());
                logger.info("{} has entered the site.", position.name());
                switch (position) {
                    case ADMIN:
                        webPageUri = CommandEnum.ADMIN_BUSES.getCurrentCommand().execute(request);
                        break;
                    case DRIVER:
                        webPageUri = CommandEnum.SHOW_APPOINTMENT.getCurrentCommand().execute(request);
                        break;
                    case DIRECTOR:
                        webPageUri = CommandEnum.SHOW_EMPLOYEES.getCurrentCommand().execute(request);
                        break;
                }

            } else {
                webPageUri = ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ERROR_403);
            }
        } else {
            webPageUri = getLoginErrorUri(request);
        }
        return webPageUri;
    }

    private String getLoginErrorUri(HttpServletRequest request) {
        request.setAttribute(ATTR_ERROR_LOGIN_PASS_MESSAGE,
                ResourceManager.MESSAGES.getProperty(MESSAGE_LOGIN_ERROR));
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_LOGIN);
    }
}
