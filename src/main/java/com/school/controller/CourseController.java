package com.school.controller;

import com.school.entity.Course;
import com.school.model.CourseDto;
import com.school.service.CourseService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Validated
@Controller("/school/course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @Operation(description = "Create a new course.")
    @Post(produces = "application/json")
    public HttpResponse<Course> createCourse(@Body CourseDto courseDto) {

        var course = this.service.create(courseDto);

        var uri = UriBuilder.of("/school/course")
                .path(Integer.toString(course.getId()))
                .build();

        return HttpResponse.created(uri).body(course);
    }

    @Operation(description = "Update a course. Attribute id is required.")
    @Put
    public HttpResponse updateCourse(@Body CourseDto courseDto) {
        this.service.update(courseDto);
        return HttpResponse.ok();
    }

    @Operation(description = "delete a course by id.")
    @Delete(uri = "/{id}")
    public HttpResponse deleteCourse(@PathVariable(name = "id") int courseId) {
        this.service.delete(courseId);
        return HttpResponse.ok();
    }

    @Operation(description = "Find a course by id.")
    @Get(uri = "/{id}", produces = "application/json")
    public HttpResponse<Course> findById(@PathVariable(name = "id") int id) {
        Course course = this.service.findById(id);
        return course == null ? HttpResponse.noContent() : HttpResponse.ok(course);
    }

    @Operation(description = "List all courses of a specific student.")
    @Get(uri = "/student/{studentId}", produces = "application/json")
    public HttpResponse<List<Course>> courseByStudent(@PathVariable(name = "studentId") int studentId) {
        var result = this.service.listCourseByStudent(studentId);
        return result.isEmpty() ? HttpResponse.noContent() : HttpResponse.ok(result);
    }

    @Operation(description = "List all courses without students.")
    @Get(uri = "/nostudent", produces = "application/json")
    public HttpResponse<List<Course>> noStudent() {
        var result = this.service.listCoursesWithoutStudents();
        return result.isEmpty() ? HttpResponse.noContent() : HttpResponse.ok(result);
    }

    @Operation(description = "List all courses.")
    @Get(produces = "application/json")
    public HttpResponse<List<Course>> allCourses() {
        var result = this.service.findAll();
        return result.isEmpty() ? HttpResponse.noContent() : HttpResponse.ok(result);
    }

}
