package com.school.service;

import com.school.entity.Student;
import com.school.exception.SchoolException;
import com.school.model.EnrollDto;

public interface EnrollmentService {

    Student enroll(EnrollDto dto) throws SchoolException;
}
