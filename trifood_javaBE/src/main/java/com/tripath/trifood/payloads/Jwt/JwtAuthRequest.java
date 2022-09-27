package com.tripath.trifood.payloads.Jwt;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;
	private String password;
}
