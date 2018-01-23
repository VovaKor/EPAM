package com.korobko.commands;

import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vova Korobko
 */
public class ShowSignUpViewCommand implements Command {

    private static final String PATH_PAGE_SIGNUP = "path.page.signup";

    @Override
    public String execute(HttpServletRequest request) {
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_SIGNUP);
    }
}
