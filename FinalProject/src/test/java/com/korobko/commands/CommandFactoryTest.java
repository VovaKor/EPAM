package com.korobko.commands;

import org.junit.*;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.PARAM_NAME_COMMAND;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vova Korobko
 */
public class CommandFactoryTest {

    private static HttpServletRequest mockRequest;

    @BeforeClass
    public static void mockRequest() {
        mockRequest = mock(HttpServletRequest.class);
    }

    @AfterClass
    public static void destroyResources() {
        mockRequest = null;
    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void defineCommand_noCommandInRequest_EmptyCommand() {
        when(mockRequest.getParameter(PARAM_NAME_COMMAND)).thenReturn(null);
        Command command = CommandFactory.defineCommand(mockRequest);
        assertSame(CommandEnum.EMPTY.getCurrentCommand(), command);
    }

    @Test
    public void defineCommand_loginCommandInRequest_LoginCommand() {
        when(mockRequest.getParameter(PARAM_NAME_COMMAND)).thenReturn("login");
        Command command = CommandFactory.defineCommand(mockRequest);
        assertSame(CommandEnum.LOGIN.getCurrentCommand(), command);
    }

    @Test
    public void defineCommand_unfamiliarCommandInRequest_EmptyCommand() {
        when(mockRequest.getParameter(PARAM_NAME_COMMAND)).thenReturn("loginlogin");
        Command command = CommandFactory.defineCommand(mockRequest);
        assertSame(CommandEnum.EMPTY.getCurrentCommand(), command);
    }
}