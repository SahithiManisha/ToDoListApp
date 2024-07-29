package com.todolist.controller;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {
    public static void main(String[] args) {
        // Example password and hash
        String password = "mysecretpassword";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        // Debug prints
        System.out.println("Password: " + password);
        System.out.println("Hashed Password: " + hashed);

        // Check password
        if (BCrypt.checkpw(password, hashed)) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match.");
        }
    }
}
