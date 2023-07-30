package com.school.repository;

import com.school.entity.Student;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.transaction.annotation.ReadOnly;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @ReadOnly
    public Optional<Student> findByUsername(String username);

    @ReadOnly
    @Query(value = "SELECT DISTINCT s FROM Student s JOIN s.courseList c WHERE c.id = :courseId ORDER BY s.name")
    public List<Student> studentByCourse(int courseId);

    @ReadOnly
    @Query(value = "SELECT s FROM Student s WHERE size(s.courseList) = 0 ORDER BY s.name")
    public List<Student> studentsWithoutCourse();

    @ReadOnly
    @Query(value = "SELECT DISTINCT s FROM Student s JOIN FETCH s.courseList ORDER BY s.name")
    public List<Student> allStudentsFetchingCourses();

}
