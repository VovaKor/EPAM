package com.korobko.commands;

import com.korobko.utils.Constants;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vova Korobko
 */
class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ResourceManager.CONFIGURATION.getProperty(Constants.PATH_PAGE_LOGIN);
        request.getSession().invalidate();
        return page;
    }
}

