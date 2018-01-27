package it;

import com.meterware.httpunit.*;
import org.junit.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.xml.sax.SAXException;

import java.io.IOException;

import static com.korobko.dao.DBColumns.PASSWORD;
import static com.korobko.utils.Constants.PARAM_NAME_COMMAND;
import static com.korobko.utils.Constants.PARAM_NAME_LOGIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Vova Korobko
 */

public class ApplicationTest {

    @ClassRule
    public static GenericContainer tomcat = new GenericContainer<>(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder -> builder
                            .from("carfleet:v2")
                            .entryPoint("service mysql start & catalina.sh run")
                            .build()))
            .withExposedPorts(8080);

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void appHasDirectorCredentialsInitializedInDB() throws IOException, SAXException {

        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(
                "http://localhost:" + tomcat.getMappedPort(8080));
        WebResponse response = conversation.getResponse(request);

        WebForm loginForm = response.getFormWithName("loginForm");
        request = loginForm.getRequest();
        request.setParameter(PARAM_NAME_COMMAND, PARAM_NAME_LOGIN);
        request.setParameter(PARAM_NAME_LOGIN, "director@director.com");
        request.setParameter(PASSWORD, "director");
        response = conversation.getResponse(request);
        assertTrue("Login not accepted",
                response.getText().contains("Director console: employees"));
        assertEquals("Page title", "EMPLOYEES", response.getTitle());
    }
}