package com.tripath.trifood.api.student.dto;

import com.tripath.trifood.entities.EClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private Long id;

    @NotEmpty
    @Size(min= 4, message = "Username must be min of 4 characters")
    private String name;

    @NotEmpty
    @Size(min = 3, max = 18, message = "Password must be from 3-18 characters")
    private String password;

    @NotEmpty
    private String fullname;

    private String stclass;

    @NotEmpty
    private String phone;

    @NotEmpty
    @Email(message = "Email address is not valid")
    private String email;

    private Float score;

    private EClass eClass;
//
//    private Set<RoleDto> roles = new HashSet<>();
}
