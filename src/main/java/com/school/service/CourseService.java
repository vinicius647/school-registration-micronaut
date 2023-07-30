package com.school.service;


import com.school.entity.Course;
import com.school.exception.SchoolException;
import com.school.model.CourseDto;

import java.util.List;

public interface CourseService {

    Course create(CourseDto courseDto);

    void update(CourseDto courseDto) throws SchoolException;

    void delete(int courseId);

    Course findById(int course);

    List<Course> listCourseByStudent(int studentId);

    List<Course> listCoursesWithoutStudents();

    List<Course> findAll();
}
