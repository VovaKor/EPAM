package com.korobko.servlets;

import com.korobko.commands.Command;
import com.korobko.commands.CommandFactory;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.korobko.utils.Constants.LOG4J_PROPERTIES_LOCATION;
import static com.korobko.utils.Constants.SERVLET_ROOT;
/**
 * An entry point of the app
 *
 * @author Vova Korobko
 */
public class MainServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath(SERVLET_ROOT);
        String filename = getInitParameter(LOG4J_PROPERTIES_LOCATION);
        if (filename != null) {
            PropertyConfigurator.configure(prefix + filename);
        }
        logger.info("Application is up and running.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Called by the servlet doGet() or doPost() methods to handle incoming requests.
     * @param request an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     * @throws ServletException if the target resource throws this exception
     * @throws IOException if the target resource throws this exception
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(request);
        String webPageUri = command.execute(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(webPageUri);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        logger.info("Application is stopped.");
    }
}
