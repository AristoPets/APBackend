package com.AristoPets.defaultAuthReg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    private String hashPassword;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Encoder(String password){
        hashPassword = password;
    }

    public Encoder(){}

    public String getHash() {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(hashPassword.getBytes("UTF-8"));
            byte[] hash = sha256.digest();
            hashPassword = String.format("%064x", new java.math.BigInteger(1, hash));
        } catch (NoSuchAlgorithmException nsae) {
            logger.error("error in getHash(){ " +
                    "MassegeDigest.getInstance(SHA-256)" +
                    "}");
        } catch (UnsupportedEncodingException uee) {
            logger.error("error in getHash(){" +
                    " sha256.udate(password.getBytes(UTF-8))" +
                    "}");
        }

        return hashPassword;
    }
}
