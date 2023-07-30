package com.school.service;

import com.school.entity.Course;
import com.school.exception.CourseNotFoundException;
import com.school.exception.SchoolException;
import com.school.mapper.CourseMapper;
import com.school.model.CourseDto;
import com.school.repository.CourseRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course create(CourseDto courseDto) {
        courseDto.setId(null);

        var course = CourseMapper.INSTANCE.toEntity(courseDto);

        course = repository.saveAndFlush(course);
        return course;
    }

    @Override
    public void update(CourseDto courseDto) throws SchoolException {
        if (courseDto.getId() == null || courseDto.getId() == 0) {
            throw new SchoolException("Invalid course id.");
        }

        repository.findById(courseDto.getId()).ifPresentOrElse(course -> {
            course.setCourseName(courseDto.getCourseName());
            repository.merge(course);
        }, () -> {
            throw new CourseNotFoundException();
        });
    }

    @Override
    public void delete(int courseId) {
        this.repository.deleteById(courseId);
    }

    @Override
    public Course findById(int courseId) {
        var optCourse = this.repository.findById(courseId);
        return optCourse.orElse(null);
    }

    @Override
    public List<Course> listCourseByStudent(int studentId) {
        return this.repository.courseByStudent(studentId);
    }

    @Override
    public List<Course> listCoursesWithoutStudents() {
        return this.repository.coursesWithoutStudent();
    }

    @Override
    public List<Course> findAll() {
        return this.repository.findAll();
    }
}
