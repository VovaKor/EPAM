package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.EmployeeService;
import com.korobko.utils.InputValidator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.ATTR_NAME_FEEDBACK_MESSAGE;
import static com.korobko.utils.Constants.PATH_PAGE_DIRECTOR_FEEDBACK;
import static com.korobko.utils.Constants.ROWS_AFFECTED;

/**
 * @author Vova Korobko
 */
public class UpdatePositionCommand implements Command {

    private static final String MESSAGE_ERROR_UPDATE_POSITION = "message.error.update.position";
    private static final String MESSAGE_POSITION_UPDATED = "message.position.updated";

    @Override
    public String execute(HttpServletRequest request) {
        String roleId = request.getParameter(DBColumns.ROLE_ID);
        String employeeId = request.getParameter(DBColumns.EMPLOYEE_ID);
        int result = 0;
        if (InputValidator.isPositiveInteger(roleId)) {
            Long empId = Long.valueOf(employeeId);
            Integer positionId = Integer.valueOf(roleId);
            if (positionId == 0) {
                positionId = null;
            }
            result = EmployeeService.INSTANCE.updatePosition(empId, positionId);
        }

        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_POSITION_UPDATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_POSITION));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);
    }
}
