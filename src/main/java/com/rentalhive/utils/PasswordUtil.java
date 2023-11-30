package com.rentalhive.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    /**
     * Encode a plain text password using BCrypt.
     *
     * @param plainPassword the plain text password
     * @return the encoded password
     */
    public String encodePassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * Check if the provided plain text password matches the encoded password.
     *
     * @param plainPassword the plain text password
     * @param encodedPassword the encoded password
     * @return true if the passwords match, false otherwise
     */
    public boolean checkPassword(String plainPassword, String encodedPassword) {
        return BCrypt.checkpw(plainPassword, encodedPassword);
    }
}
