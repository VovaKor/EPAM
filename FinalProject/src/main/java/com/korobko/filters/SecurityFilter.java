package com.korobko.filters;

import com.korobko.security.SecurityConfiguration;
import com.korobko.utils.Authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.korobko.security.SecurityConfiguration.SECURITY_LEVEL_ALL;
import static com.korobko.security.SecurityConfiguration.SECURITY_LEVEL_AUTH;
import static com.korobko.utils.Constants.PARAM_NAME_COMMAND;

/**
 * @author Vova Korobko
 */
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        SecurityConfiguration configuration = SecurityConfiguration.INSTANCE;
        String securityLevel = configuration.getSecurityLevel(request.getParameter(PARAM_NAME_COMMAND));
        HttpSession session = request.getSession();
        if (SECURITY_LEVEL_ALL.equals(securityLevel)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (SECURITY_LEVEL_AUTH.equals(securityLevel)
                && Authentication.isEmployeeLoggedIn(session)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (Authentication.getSecurityLevel(session).equals(securityLevel)
                && Authentication.isEmployeeLoggedIn(session)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public void destroy() {

    }
}
