package com.school.service;

import com.school.entity.Student;
import com.school.exception.SchoolException;
import com.school.exception.UsernameAlreadyExistsException;
import com.school.model.StudentDto;

import java.util.List;

public interface StudentService {

    Student create(StudentDto studentDto) throws UsernameAlreadyExistsException;

    void update(StudentDto studentDto) throws SchoolException;

    void delete(int studentId);

    Student findById(int studentId);

    List<Student> listStudentByCourse(int courseId);

    List<Student> listStudentsWithoutCourses();

    List<Student> listAllStudentsEnrolled();

}
