package com.korobko.commands;

import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vova Korobko
 */
class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ResourceManager.CONFIGURATION.getProperty("path.page.index");
        request.getSession().invalidate();
        return page;
    }
}

