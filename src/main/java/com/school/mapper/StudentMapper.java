package com.school.mapper;

import com.school.entity.Student;
import com.school.model.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentDto dto);

    StudentDto toDto(Student entity);
}
