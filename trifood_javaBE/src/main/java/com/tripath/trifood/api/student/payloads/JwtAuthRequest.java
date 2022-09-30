package com.tripath.trifood.api.student.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;
	private String password;
}
