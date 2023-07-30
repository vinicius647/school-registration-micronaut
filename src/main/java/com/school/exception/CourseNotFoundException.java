package com.school.exception;

public class CourseNotFoundException extends SchoolException {

    public CourseNotFoundException() {
        super("Course not found.");
    }
}
