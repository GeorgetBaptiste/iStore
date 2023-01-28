package com.istore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashPassword {
    private String hashPassword;

    public HashPassword(char[] password) throws NoSuchAlgorithmException {
        hashPassword(Arrays.toString(password));
    }

    private void hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte[] hash = msg.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        this.hashPassword = s.toString();
    }

    public String getHashPassword() {
        return hashPassword;
    }
}
