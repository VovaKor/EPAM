package com.korobko.servlets;

import com.korobko.commands.Command;
import com.korobko.commands.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vova Korobko
 */
public class MainServlet extends HttpServlet {
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
     * todo
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(request);
        String webPageUri = command.execute(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(webPageUri);
        dispatcher.forward(request, response);
    }

}
