package com.korobko.commands;

import com.korobko.dao.EmployeeDao;
import com.korobko.dao.NamesDao;
import com.korobko.entities.Employee;
import com.korobko.exceptions.TransactionException;
import com.korobko.services.EmployeeService;
import com.korobko.utils.connection.TransactionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.korobko.dao.DBColumns.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vova Korobko
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TransactionManager.class)
public class RegisterCommandTest {

    private static EmployeeDao mockedEmployeeDao;
    private static NamesDao mockedNamesDao;
    private static HttpServletRequest mockedRequest;

    @BeforeClass
    public static void mockResources() {
        mockedRequest = mock(HttpServletRequest.class);
        mockedEmployeeDao = mock(EmployeeDao.class);
        mockedNamesDao = mock(NamesDao.class);
    }

    @AfterClass
    public static void destroyResources() {
        mockedRequest = null;
        mockedEmployeeDao = null;
        mockedNamesDao = null;
    }

    @Test
    public void execute_validInput_access_feedback_jsp() {
        String email = "admin100@admin.com";
        String password = "admin100";
        String firstName = "Admin";
        String patronymic = "Adminovich";
        String lastName = "Hundredth";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(null);
        when(mockedEmployeeDao.insertEmployee(email, password)).thenReturn(1);
        when(mockedNamesDao.insertNames(firstName, patronymic, lastName)).thenReturn(1);

        PowerMockito.mockStatic(TransactionManager.class);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "namesDao", mockedNamesDao);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/access_feedback.jsp", resultJSP);

    }

    @Test
    public void execute_emptyEmail_login_jsp() throws SQLException, TransactionException {
        String email = "";
        String password = "admin100";
        String firstName = "Admin";
        String patronymic = "Adminovich";
        String lastName = "Hundredth";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_emptyPassword_login_jsp() throws SQLException, TransactionException {
        String email = "admin100@admin.com";
        String password = "";
        String firstName = "Admin";
        String patronymic = "Adminovich";
        String lastName = "Hundredth";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_emptyFirstName_login_jsp() throws SQLException, TransactionException {
        String email = "admin100@admin.com";
        String password = "admin100";
        String firstName = "";
        String patronymic = "Adminovich";
        String lastName = "Hundredth";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);
        PowerMockito.mockStatic(TransactionManager.class);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "namesDao", mockedNamesDao);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_emptyLastName_login_jsp() throws SQLException, TransactionException {
        String email = "admin100@admin.com";
        String password = "admin100";
        String firstName = "Admin";
        String patronymic = "Adminovich";
        String lastName = "";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);
        PowerMockito.mockStatic(TransactionManager.class);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "namesDao", mockedNamesDao);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_emailNotValid_login_jsp() throws SQLException, TransactionException {
        String email = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!";
        String password = "admin100";
        String firstName = "Admin";
        String patronymic = "Adminovich";
        String lastName = "Hundredth";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_passwordNotValid_login_jsp() throws SQLException, TransactionException {
        String email = "admin100@admin.com";
        String password = "adm";
        String firstName = "Admin";
        String patronymic = "Adminovich";
        String lastName = "Hundredth";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_emailExistInDB_login_jsp() {
        String email = "admin100@admin.com";
        String password = "admin100";
        String firstName = "Admin";
        String patronymic = "Adminovich";
        String lastName = "Hundredth";
        when(mockedRequest.getParameter(EMAIL)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn(password);
        when(mockedRequest.getParameter(FIRST_NAME)).thenReturn(firstName);
        when(mockedRequest.getParameter(PATRONYMIC)).thenReturn(patronymic);
        when(mockedRequest.getParameter(LAST_NAME)).thenReturn(lastName);
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(new Employee());

        PowerMockito.mockStatic(TransactionManager.class);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);

        String resultJSP = CommandEnum.REGISTER.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);

    }
}