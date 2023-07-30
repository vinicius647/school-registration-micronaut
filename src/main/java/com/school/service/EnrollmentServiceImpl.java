package com.school.service;

import com.school.entity.Student;
import com.school.exception.*;
import com.school.model.EnrollDto;
import com.school.repository.CourseRepository;
import com.school.repository.StudentRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.function.BiPredicate;

@Singleton
public class EnrollmentServiceImpl implements EnrollmentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public EnrollmentServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student enroll(EnrollDto dto) throws SchoolException {

        var optStudent = this.studentRepository.findById(dto.getStudentId());
        var student = optStudent.orElseThrow(() -> new StudentNotFoundException());

        var optCourse = this.courseRepository.findById(dto.getCourseId());
        var course = optCourse.orElseThrow(() -> new CourseNotFoundException());

        BiPredicate<Integer, Integer> checkLimitExceeded = (q, l) -> {
            return q >= l;
        };

        if (checkLimitExceeded.test(student.getCourseList().size(), 5)) {
            throw new CourseLimitExceededException();
        }

        if (checkLimitExceeded.test(course.getStudentList().size(), 50)) {
            throw new StudentLimitExceededException();
        }

        student.getCourseList().add(course);
        course.getStudentList().add(student);
        this.courseRepository.save(course);
        this.studentRepository.save(student);

        return student;
    }
}
