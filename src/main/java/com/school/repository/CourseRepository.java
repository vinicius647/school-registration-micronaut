package com.school.repository;

import com.school.entity.Course;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.transaction.annotation.ReadOnly;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @ReadOnly
    @Query(value = "SELECT DISTINCT c FROM Course c JOIN c.studentList s WHERE s.id = :studentId ORDER BY c.courseName")
    public List<Course> courseByStudent(int studentId);

    @ReadOnly
    @Query(value = "SELECT c FROM Course c WHERE size(c.studentList) = 0 ORDER BY c.courseName")
    public List<Course> coursesWithoutStudent();
}
