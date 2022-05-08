package com.cnpm.webadmin.until;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static Boolean isTrue(Object value) {

        if (value == null) {
            return false;
        }

        if (value instanceof String) {
            return !((String) value).trim().isEmpty();
        }

        if (value instanceof List) {
            return !((List) value).isEmpty();
        }

        /*if (value instanceof Number) {
            return ((Number) value).intValue() > 0;
        }*/

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Collection) {
            return !((Collection) value).isEmpty();
        }

        if (value instanceof Object[]) {
            return ((Object[]) value).length > 0;
        }

        return true;
    }

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    public static boolean isEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
