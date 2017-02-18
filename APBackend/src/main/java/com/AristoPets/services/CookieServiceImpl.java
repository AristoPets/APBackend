package com.AristoPets.services;


import com.AristoPets.dao.CookieRepository;
import com.AristoPets.entity.User;
import com.AristoPets.entity.UserCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class CookieServiceImpl implements CookieService {

    private final CookieRepository cookieRepository;

    @Autowired
    public CookieServiceImpl(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    @Override
    public String setCookie(User user) {
        UserCookie userCookie = cookieRepository.findByUserId(user.getId());
        if (userCookie == null) {
            String uuID = UUID.randomUUID().toString();
            userCookie = new UserCookie();
            userCookie.setUUID(uuID);
            userCookie.setUser(user);
            cookieRepository.save(userCookie);
        }
        return userCookie.getUUID();
    }

    @Override
    public UserCookie getCookie(long userId) {
        return cookieRepository.findByUserId(userId);
    }

    @Override
    public UserCookie getCookie(String UUID) {
        return cookieRepository.findByuuId(UUID);
    }

    @Override
    public UserCookie getCookieWithUserData(String UUID) {
        return cookieRepository.findCookieWithUserData(UUID);
    }

}
