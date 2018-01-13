package com.korobko.commands;

import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vova Korobko
 */
class EmptyCommand implements Command {

    private static final String PATH_PAGE_ERROR_404 = "path.page.error_404";

    @Override
    public String execute(HttpServletRequest request) {
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ERROR_404);
    }
}

