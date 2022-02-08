package com.fjmg.inventory.utils;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtils
{
    static final String EMAIL_PATTERN = Patterns.EMAIL_ADDRESS.pattern();
    static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])(?!.*\\s).{8,20}";
    static final String USER_PATTERN = "^[A-Za-z]\\w{1,29}$";
    static final String DEPENDECY_SHORT_NAME_PATTERN = "^[a-zA-Z0-9]+$";



    public static boolean isPasswordValid(String password)
    {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static boolean isEmailValid(String email)
    {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isUsernameValid(String username)
    {
        Pattern pattern = Pattern.compile(USER_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static boolean isDependecyShortNameValid(String shortName) {
        return Pattern.compile(DEPENDECY_SHORT_NAME_PATTERN)
                .matcher(shortName)
                .matches();
    }
}
