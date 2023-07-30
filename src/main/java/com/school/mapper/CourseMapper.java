package com.school.mapper;

import com.school.entity.Course;
import com.school.model.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toEntity(CourseDto dto);

    CourseDto toDto(Course entity);
}
