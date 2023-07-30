package com.school.controller;

import com.school.entity.Student;
import com.school.model.EnrollDto;
import com.school.model.StudentDto;
import com.school.service.EnrollmentService;
import com.school.service.StudentService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Validated
@Controller("/school/student")
public class StudentController {

    private final StudentService service;
    private final EnrollmentService enrollmentService;

    public StudentController(StudentService service, EnrollmentService enrollmentService) {
        this.service = service;
        this.enrollmentService = enrollmentService;
    }

    @Operation(description = "Create a student. Username will be validated, if it already exists a bad request will be returned.")
    @Post
    public HttpResponse<Student> createStudent(@Body StudentDto studentDto) {

        var student = this.service.create(studentDto);

        var uri = UriBuilder.of("/school/course")
                .path(Integer.toString(student.getId()))
                .build();

        return HttpResponse.created(uri).body(student);
    }

    @Operation(description = "Update a student. Username will be validated, if it already exists a bad request will be returned.")
    @Put
    public HttpResponse updateStudent(@Body StudentDto studentDto) {
        this.service.update(studentDto);
        return HttpResponse.ok();
    }

    @Operation(description = "Delete a student by id.")
    @Delete(uri = "/{id}")
    public HttpResponse deleteStudent(@PathVariable(name = "id") int studentId) {
        this.service.delete(studentId);
        return HttpResponse.ok();
    }

    @Operation(description = "Enroll the student in a new course. It will be validated if the course not exceeded 50 students and if the student not exceeded 5 courses.")
    @Post("/enroll")
    public HttpResponse<Student> enroll(@Body EnrollDto dto) {
        var student = this.enrollmentService.enroll(dto);

        var uri = UriBuilder.of("/school/course/student")
                .path(Integer.toString(student.getId()))
                .build();

        return HttpResponse.created(uri).body(student);
    }

    @Operation(description = "Find student by id.")
    @Get(uri = "{id}")
    public HttpResponse<Student> findById(@PathVariable(name = "id") int id) {
        Student student = this.service.findById(id);
        return student == null ? HttpResponse.noContent() : HttpResponse.ok(student);
    }

    @Operation(description = "List all students are enrolled in a specific course.")
    @Get(uri = "/course/{courseId}")
    public HttpResponse<List<Student>> studentByCourse(@PathVariable(name = "courseId") int courseId) {
        var result = this.service.listStudentByCourse(courseId);
        return result.isEmpty() ? HttpResponse.noContent() : HttpResponse.ok(result);
    }

    @Operation(description = "List all students without courses.")
    @Get(uri = "/nocourse")
    public HttpResponse<List<Student>> noCourse() {
        var result = this.service.listStudentsWithoutCourses();
        return result.isEmpty() ? HttpResponse.noContent() : HttpResponse.ok(result);
    }

    @Operation(description = "List all enrolled students.")
    @Get(uri = "/enrolled")
    public HttpResponse<List<Student>> allStudentsEnrolled() {
        var result = this.service.listAllStudentsEnrolled();
        return result.isEmpty() ? HttpResponse.noContent() : HttpResponse.ok(result);
    }
}
