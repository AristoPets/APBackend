package com.AristoPets.entity;

import javax.persistence.*;

@Entity
@Table(name = "password")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String invalidUserInput;

    public UserRegistration(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInvalidUserInput() { return invalidUserInput; }

    public void setInvalidUserInput(String invalidUserInput) { this.invalidUserInput = invalidUserInput; }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}