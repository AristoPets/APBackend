package com.AristoPets.dao;


import com.AristoPets.entity.UserCookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CookieRepository extends JpaRepository<UserCookie, Long> {
    UserCookie findByUserId(long userId);

    UserCookie findByuuId(String uuId);

    @Query("SELECT c FROM UserCookie c JOIN FETCH c.user WHERE c.uuId = :uuid")
    UserCookie findCookieWithUserData(@Param("uuid") String uuid);
}
