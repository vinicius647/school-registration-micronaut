package com.school.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class CourseDto {

    private Integer id;
    private String courseName;

}
