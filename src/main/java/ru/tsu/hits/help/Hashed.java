package ru.tsu.hits.help;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashed {
    public String getHashedPassword(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {

        }
        var bytes = md5.digest(password.getBytes());
        var builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02X ", b));
        }
        return builder.toString();
    }
}
