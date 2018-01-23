package com.korobko.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.korobko.utils.Constants.PARAM_NAME_COMMAND;

/**
 * @author Vova Korobko
 */
public class CommandFactory {
    private static Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    private CommandFactory() {}

    /**
     * Defines a Command from given request
     * @param request an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     * @return a Command object
     */
    public static Command defineCommand(HttpServletRequest request) {
        Command current = CommandEnum.EMPTY.getCurrentCommand();

        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (Objects.isNull(action) || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error("Unfamiliar command", e);
        }
        return current;
    }
}
