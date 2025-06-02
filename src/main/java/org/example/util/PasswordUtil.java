package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPassword(String password) {
        //this method gets a password as a String and encrypts it in BCrypt and returns token
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        //this method checks a password to be checked with encrypted passord and returns result
        return BCrypt.checkpw(password, hashedPassword);
    }
}
