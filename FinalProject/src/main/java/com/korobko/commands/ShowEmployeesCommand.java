package com.korobko.commands;

import com.korobko.entities.Employee;
import com.korobko.entities.EmployeePosition;
import com.korobko.services.EmployeeService;
import com.korobko.services.PositionService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vova Korobko
 */
public class ShowEmployeesCommand implements Command {

    private static final String ATTR_NAME_EMP_LIST = "emp_list";
    private static final String ATTR_NAME_POS_LIST = "pos_list";
    private static final String PATH_PAGE_DIRECTOR_EMPLOYEES = "path.page.director.employees";

    @Override
    public String execute(HttpServletRequest request) {
        List<Employee> employees = EmployeeService.INSTANCE.getSubordinates();
        List<EmployeePosition> positions = PositionService.INSTANCE.getSubordinatePositions();
        request.setAttribute(ATTR_NAME_EMP_LIST, employees);
        request.setAttribute(ATTR_NAME_POS_LIST, positions);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_EMPLOYEES);
    }
}
