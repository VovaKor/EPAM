package com.korobko.commands;

import com.korobko.services.EmployeeService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import static com.korobko.dao.DBColumns.*;
import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class RegisterCommand implements Command {

    private static final String PATH_PAGE_ACCESS_FEEDBACK = "path.page.access.feedback";
    private static final String MESSAGE_SUCCESS_REGISTER = "message.success.register";
    private static final String MESSAGE_ERROR_REGISTER = "message.error.register";

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String patronymic = request.getParameter(PATRONYMIC);
        String lastName = request.getParameter(LAST_NAME);
        int result = EmployeeService.INSTANCE.register(email, password, firstName, patronymic, lastName);
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_SUCCESS_REGISTER));
            return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ACCESS_FEEDBACK);
        }
        if (result == ERROR_CODE) {
            request.setAttribute(ATTR_ERROR_LOGIN_PASS_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_EMAIL_EXIST));
        } else {
            request.setAttribute(ATTR_ERROR_LOGIN_PASS_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_REGISTER));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_LOGIN);
    }
}
