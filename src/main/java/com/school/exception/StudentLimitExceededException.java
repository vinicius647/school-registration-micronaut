package com.school.exception;

public class StudentLimitExceededException extends SchoolException {

    public StudentLimitExceededException() {
        super("Course has exceeded the student limit.");
    }
}
