package com.AristoPets.webconfig.filters;


import com.AristoPets.entity.User;
import com.AristoPets.entity.UserCookie;
import com.AristoPets.services.CookieService;
import com.AristoPets.webconfig.CookieHelper;
import com.AristoPets.webconfig.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Autowired
    private CookieService cookieService;

    private FilterConfig filterConfig;

    public final static String NEED_AUTH_PARAM = "needAuth";

    public final static String INVALID_REG_PARAM = "invalidReg";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (this.filterConfig == null) {
            throw new ServletException();
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserCookie userCookie;
        Cookie[] cookies = request.getCookies();
        String userUUID;
        User user;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CookieHelper.authCookie)) {
                    userUUID = cookie.getValue();
                    userCookie = cookieService.getCookieWithUserData(userUUID);
                    if (userCookie != null) {
                        user = userCookie.getUser();
                        request.setAttribute("userData", user);
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        CookieHelper.removeCookie(response, CookieHelper.authCookie);  // user can't have incorrect COOKIE value with correct cookie name
                    }
                }
            }
        }

        String requestUri = request.getRequestURI(); // user is able to use api, only when has correct COOKIE
        for(int i = 0; i < URLS.AUTH_FILTER_STATIC_PAGES.length; i++) {
            if (requestUri.contains(URLS.AUTH_FILTER_STATIC_PAGES[i])) {
                response.sendRedirect("/#auth");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
