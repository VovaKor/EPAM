package com.korobko.commands;

import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.PATH_PAGE_LOGIN;

/**
 * @author Vova Korobko
 */
class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_LOGIN);
    }
}

