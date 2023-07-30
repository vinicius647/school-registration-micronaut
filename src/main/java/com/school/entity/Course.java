package com.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Serdeable
@Getter
@Setter
@ToString
@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_name")
    private String courseName;
    @JoinTable(name = "course_student", joinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> studentList = new java.util.ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @JsonIgnore
    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
