package com.AristoPets.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;
    //private final String passwordRecoveryLink = "https://aristo-pets.com/passwordRecovery";

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Async
    public void sendNotificationOfRegistration(String email) {
        //send email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("aristo.ppets@gmail.com");
        mail.setSubject("confirm registration");
        mail.setText("Welcome to AristoPets " +
                "http://aristo-pets.com/");
        javaMailSender.send(mail);
    }

    /*//TODO
    public void sendNotificationOfPasswordRecovery(UserDto userDto) throws MailParseException, MailAuthenticationException, MailSendException{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(userDto.getEmail());
        mail.setFrom("aristo.ppets@gmail.com");
        mail.setSubject("UserRegistration recovery");
        mail.setText("Hi " + userDto.getEmail()
                        + ". Go to this link to reset your password"
                        + passwordRecoveryLink);

        javaMailSender.send(mail);
    }*/
}
