package com.AristoPets.webconfig;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
    public static final int MAX_AGE = 2592000;
    public static final String authCookie = "aristopets_auth";

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void addCookie(HttpServletResponse response, String name, String uuid, int maxAge) {
        Cookie cookie = new Cookie(name, uuid);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void addSessionCookie(HttpServletResponse response, String name, String uuid){
        Cookie cookie = new Cookie(name, uuid);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        addCookie(response, name, null, 0);
    }
}