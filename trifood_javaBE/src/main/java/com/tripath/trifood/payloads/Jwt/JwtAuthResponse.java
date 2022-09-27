package com.tripath.trifood.payloads.Jwt;

import com.tripath.trifood.payloads.Dto.StudentDto;
import lombok.Data;

@Data
public class JwtAuthResponse {
	private String token;
	private StudentDto student;
}
