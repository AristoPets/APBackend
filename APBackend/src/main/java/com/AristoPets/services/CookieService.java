package com.AristoPets.services;


import com.AristoPets.entity.User;
import com.AristoPets.entity.UserCookie;


public interface CookieService {

    String setCookie(User user);

    UserCookie getCookie(long userId);

    UserCookie getCookie(String UUID);

    UserCookie getCookieWithUserData(String UUID);

}
