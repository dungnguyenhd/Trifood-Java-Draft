package com.tripath.trifood.api.student.payloads;

import com.tripath.trifood.api.student.dto.StudentDto;
import lombok.Data;

@Data
public class JwtAuthResponse {
	private String token;
	private StudentDto student;
}
