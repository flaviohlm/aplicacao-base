package br.gov.to.secad.main.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    // Email Regex java
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /**
     * This method validates the input email address with EMAIL_REGEX pattern
     *
     * @param email .
     * @return boolean
     */
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
