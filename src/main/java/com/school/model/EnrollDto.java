package com.school.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
public class EnrollDto {

    private int studentId;
    private int courseId;
}
