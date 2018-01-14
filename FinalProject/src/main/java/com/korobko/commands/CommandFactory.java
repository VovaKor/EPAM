package com.korobko.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.korobko.utils.Constants.PARAM_NAME_COMMAND;

/**
 * @author Vova Korobko
 */
public class CommandFactory {

    private CommandFactory() {}

    /**
     * Defines a Command from given request
     * @param request an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     * @return a Command object
     */
    public static Command defineCommand(HttpServletRequest request) {
        Command current = new EmptyCommand();

        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (Objects.isNull(action) || action.isEmpty()) {
            return current;
        }

        CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
        current = currentEnum.getCurrentCommand();

        return current;
    }
}
