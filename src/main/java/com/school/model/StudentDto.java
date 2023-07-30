package com.school.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class StudentDto {

    private Integer id;
    private String name;
    private String username;
    private String password;

}
