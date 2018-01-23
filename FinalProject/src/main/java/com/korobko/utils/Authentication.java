package com.korobko.utils;

import com.korobko.dao.DBColumns;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public final class Authentication {
    private Authentication() {
    }

    private static final int EMAIL_MIN_LENGTH = 6;
    private static final int EMAIL_MAX_LENGTH = 125;
    private static final int PASSWORD_MIN_LENGTH = 4;
    private static final int PASSWORD_MAX_LENGTH = 128;
    private static final String EMAIL_REGEXP = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

    public static boolean isCredentialsValid(String login, String pass) {
        if (Objects.isNull(login)) {
            return false;
        }
        int loginLength = login.length();
        return loginLength >= EMAIL_MIN_LENGTH
                && loginLength <= EMAIL_MAX_LENGTH
                && login.matches(EMAIL_REGEXP)
                && isPasswordValid(pass);
    }

    public static boolean isPasswordsMatches(String passFromJsp, String passFromDB) {
        return Objects.equals(HashGenerator.generateSHA512(passFromJsp), passFromDB);
    }

    public static boolean isEmployeeLoggedIn(HttpSession session) {
        return Objects.nonNull(session.getAttribute(DBColumns.EMPLOYEE_ID));
    }

    public static String getSecurityLevel(HttpSession session) {
        String role = (String) session.getAttribute(DBColumns.ROLE);
        return Objects.nonNull(role) ? role : "";
    }

    public static boolean isPasswordValid(String password) {
        if (Objects.isNull(password)) {
            return false;
        }
        int passLength = password.length();
        return passLength >= PASSWORD_MIN_LENGTH
                && passLength <= PASSWORD_MAX_LENGTH;
    }
}
