package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.entities.Employee;
import com.korobko.entities.EmployeePosition;
import com.korobko.services.EmployeeService;
import com.korobko.utils.Constants;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class ProfileCommand implements Command {

    private static final String PATH_PAGE_DIRECTOR_PROFILE = "path.page.director.profile";
    private static final String PATH_PAGE_DRIVER_PROFILE = "path.page.driver.profile";
    private static final String PATH_PAGE_ADMIN_PROFILE = "path.page.admin.profile";
    private static final String ATTR_NAME_EMPLOYEE = "employee";

    @Override
    public String execute(HttpServletRequest request) {
        String webPageUri = ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ERROR_403);
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(DBColumns.ROLE);
        if (Objects.nonNull(role)) {
            setAttribute(session, request);
            switch (EmployeePosition.valueOf(role)) {
                case ADMIN: webPageUri = ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_PROFILE);
                    break;
                case DRIVER: webPageUri = ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DRIVER_PROFILE);
                    break;
                case DIRECTOR: webPageUri = ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_PROFILE);
                    break;
            }
        }
        return webPageUri;
    }

    private void setAttribute(HttpSession session, HttpServletRequest request) {
        Employee employee = EmployeeService.INSTANCE.getEmployeeById((Long) session.getAttribute(DBColumns.EMPLOYEE_ID));
        request.setAttribute(ATTR_NAME_EMPLOYEE, employee);
    }
}
