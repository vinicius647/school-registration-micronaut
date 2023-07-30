package com.school.exception;

public class UsernameAlreadyExistsException extends SchoolException {

    public UsernameAlreadyExistsException() {
        super("Username already exists.");
    }
}
