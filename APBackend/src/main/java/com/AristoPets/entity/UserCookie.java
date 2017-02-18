package com.AristoPets.entity;

import javax.persistence.*;


@Entity
@Table(name = "Cookies")
public class UserCookie {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "uuid")
    private String uuId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_id", updatable = false, insertable = false)
    private long userId;

    public UserCookie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUUID() {
        return uuId;
    }

    public void setUUID(String UUID) {
        this.uuId = UUID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
