package com.school.exception;

public class CourseLimitExceededException extends SchoolException {

    public CourseLimitExceededException() {
        super("Student has exceeded the course limit.");
    }
}
