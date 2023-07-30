package com.school.service;

import com.school.entity.Student;
import com.school.exception.SchoolException;
import com.school.exception.StudentNotFoundException;
import com.school.exception.UsernameAlreadyExistsException;
import com.school.mapper.StudentMapper;
import com.school.model.StudentDto;
import com.school.repository.StudentRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student create(StudentDto studentDto) throws UsernameAlreadyExistsException {

        var optUsername = this.repository.findByUsername(studentDto.getUsername());
        if (optUsername.isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        studentDto.setId(null);
        var student = StudentMapper.INSTANCE.toEntity(studentDto);

        return this.repository.saveAndFlush(student);
    }

    @Override
    public void update(StudentDto studentDto) throws SchoolException {
        if (studentDto.getId() == null || studentDto.getId() == 0) {
            throw new SchoolException("Invalid student id.");
        }

        var optUsername = this.repository.findByUsername(studentDto.getUsername());
        optUsername.ifPresentOrElse(student -> {
            if (!student.getId().equals(studentDto.getId())) {
                throw new UsernameAlreadyExistsException();
            } else {
                student.setUsername(studentDto.getUsername());
                student.setName(studentDto.getName());
                this.repository.merge(student);
            }
        }, () -> this.repository.findById(studentDto.getId()).ifPresentOrElse(student -> {
            student.setUsername(studentDto.getUsername());
            student.setName(studentDto.getName());
            this.repository.merge(student);
        }, () -> {
            throw new StudentNotFoundException();
        }));

    }

    @Override
    public void delete(int studentId) {
        this.repository.deleteById(studentId);
    }

    @Override
    public Student findById(int studentId) {
        var optStudent = this.repository.findById(studentId);
        return optStudent.orElse(null);
    }

    @Override
    public List<Student> listStudentByCourse(int courseId) {
        return this.repository.studentByCourse(courseId);
    }

    @Override
    public List<Student> listStudentsWithoutCourses() {
        return this.repository.studentsWithoutCourse();
    }

    @Override
    public List<Student> listAllStudentsEnrolled() {
        return this.repository.allStudentsFetchingCourses();
    }
}
