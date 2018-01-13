package com.korobko.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vova Korobko
 */
public interface Command {
    String execute(HttpServletRequest request);
}

