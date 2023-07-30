package com.school.exception;

public class StudentNotFoundException extends SchoolException {

    public StudentNotFoundException() {
        super("Student not found.");
    }
}
