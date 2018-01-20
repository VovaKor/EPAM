package com.korobko.commands;

import com.korobko.dao.*;
import com.korobko.entities.Employee;
import com.korobko.entities.EmployeePosition;
import com.korobko.services.*;
import com.korobko.utils.connection.TransactionManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.korobko.dao.DBColumns.PASSWORD;
import static com.korobko.utils.Constants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Vova Korobko
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TransactionManager.class, PaginationService.class})
public class LoginCommandTest {

    private static HttpServletRequest mockedRequest;
    private static EmployeeDao mockedEmployeeDao;
    private static RolesDao mockedRolesDao;
    private static AppointmentDao mockedAppointmentDao;
    private static BusDao mockedBusDao;


    @BeforeClass
    public static void mockResources() {
        mockedRequest = mock(HttpServletRequest.class);
        mockedEmployeeDao = mock(EmployeeDao.class);
        mockedRolesDao = mock(RolesDao.class);
        mockedAppointmentDao = mock(AppointmentDao.class);
        mockedBusDao = mock(BusDao.class);
    }

    @AfterClass
    public static void destroyResources() {
        mockedRequest = null;
        mockedEmployeeDao = null;
        mockedRolesDao = null;
        mockedAppointmentDao = null;
        mockedBusDao = null;
    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void execute_validDirectorCredentials_employees_jsp() {
        String email = "director@director.com";
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword("376785415685b3ea67620da49571f303ea750dd30e6543f001d3d629115dcc5e3bcf52af35c6c34d10fa27f79e11e6482cc3e725d7fb856ae127d54a7c5d2d22");
        employee.setPosition(EmployeePosition.DIRECTOR);
        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("director");
        when(mockedRequest.getSession()).thenReturn(createTestSession());
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(employee);
        when(mockedRolesDao.getSubordinatePositions()).thenReturn(new ArrayList<>());
        Whitebox.setInternalState(PositionService.INSTANCE, "rolesDao", mockedRolesDao);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/director/employees.jsp", resultJSP);
    }

    @Test
    public void execute_validDriverCredentials_appointment_jsp() {
        String email = "driver1@driver.com";
        Employee employee = new Employee();
        employee.setEmployeeId(45L);
        employee.setEmail(email);
        employee.setPassword("070affcf2e7799d7b353db1a67ba5871ca2c4d454ef282ca6ef00d2ef67408aba566365fb29154426e7027fc5c2b794484ee1cd003ce00f77756fe0806627d17");
        employee.setPosition(EmployeePosition.DRIVER);
        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("driver1");
        when(mockedRequest.getSession()).thenReturn(createTestSession());
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(employee);

        Whitebox.setInternalState(AppointmentService.INSTANCE, "appointmentDao", mockedAppointmentDao);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/driver/appointment.jsp", resultJSP);
    }

    @Test
    public void execute_validAdminCredentials_admin_buses_jsp() {
        String email = "admin1@admin.com";
        Employee employee = new Employee();
        employee.setEmployeeId(45L);
        employee.setEmail(email);
        employee.setPassword("58b5444cf1b6253a4317fe12daff411a78bda0a95279b1d5768ebf5ca60829e78da944e8a9160a0b6d428cb213e813525a72650dac67b88879394ff624da482f");
        employee.setPosition(EmployeePosition.ADMIN);

        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("admin1");
        when(mockedRequest.getSession()).thenReturn(createTestSession());
        PowerMockito.mockStatic(TransactionManager.class);
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(employee);
        PowerMockito.suppress(PowerMockito.method(PaginationService.class, "getPageAmount", DaoType.class));
        Whitebox.setInternalState(BusService.INSTANCE, "busDao", mockedBusDao);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);

        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/admin/admin_buses.jsp", resultJSP);
    }

    @Test
    public void execute_nonValidEmail_login_jsp() {
        String email = "employee@admincom";
        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("admin1");
        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_nonValidPassword_login_jsp() {
        String email = "employee@admin.com";
        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("adm");
        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/access/login.jsp", resultJSP);
    }

    @Test
    public void execute_emailNotInDB_error403() {
        String email = "employee@admin.com";
        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("admin");
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(null);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/errors/error_403.jsp", resultJSP);
    }

    @Test
    public void execute_notAssignedPositionToEmployee_error403() {
        String email = "admin1@admin.com";
        Employee employee = new Employee();
        employee.setEmployeeId(45L);
        employee.setEmail(email);
        employee.setPassword("58b5444cf1b6253a4317fe12daff411a78bda0a95279b1d5768ebf5ca60829e78da944e8a9160a0b6d428cb213e813525a72650dac67b88879394ff624da482f");

        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("admin1");
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(employee);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/errors/error_403.jsp", resultJSP);
    }

    @Test
    public void execute_incorrectPassword_error403() {
        String email = "admin1@admin.com";
        Employee employee = new Employee();
        employee.setEmployeeId(45L);
        employee.setEmail(email);
        employee.setPassword("58b5444cf1b6253a4317fe12daff411a78bda0a95279b1d5768ebf5ca60829e78da944e8a9160a0b6d428cb213e813525a72650dac67b88879394ff624da482f");
        employee.setPosition(EmployeePosition.ADMIN);
        when(mockedRequest.getParameter(PARAM_NAME_LOGIN)).thenReturn(email);
        when(mockedRequest.getParameter(PASSWORD)).thenReturn("admin");
        when(mockedEmployeeDao.findEmployeeByEmail(email)).thenReturn(employee);
        Whitebox.setInternalState(EmployeeService.INSTANCE, "employeeDao", mockedEmployeeDao);
        String resultJSP = CommandEnum.LOGIN.getCurrentCommand().execute(mockedRequest);
        assertEquals("/WEB-INF/jsp/errors/error_403.jsp", resultJSP);
    }
    private HttpSession createTestSession() {
        return new HttpSession() {
            private Map<String, Object> attributes = new HashMap<>();
            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int interval) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String name) {
                return attributes.get(name);
            }

            @Override
            public Object getValue(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String name, Object value) {
                attributes.put(name, value);
            }

            @Override
            public void putValue(String name, Object value) {

            }

            @Override
            public void removeAttribute(String name) {

            }

            @Override
            public void removeValue(String name) {

            }

            @Override
            public void invalidate() {

            }

            @Override
            public boolean isNew() {
                return false;
            }
        };
    }
}