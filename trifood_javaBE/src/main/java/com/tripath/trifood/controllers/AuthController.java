package com.tripath.trifood.controllers;

import java.security.Principal;
import javax.validation.Valid;
import com.tripath.trifood.exceptions.ApiException;
import com.tripath.trifood.models.Student;
import com.tripath.trifood.payloads.Dto.StudentDto;
import com.tripath.trifood.payloads.Jwt.JwtAuthRequest;
import com.tripath.trifood.payloads.Jwt.JwtAuthResponse;
import com.tripath.trifood.repositories.StudentRepository;
import com.tripath.trifood.security.JwtTokenHelper;
import com.tripath.trifood.services.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private StudentService studentService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setStudent(this.mapper.map((Student) userDetails, StudentDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<StudentDto> registerStudent(@Valid @RequestBody StudentDto studentDto) {
		StudentDto registeredStudent = this.studentService.registerNewStudent(studentDto);
		return new ResponseEntity<>(registeredStudent, HttpStatus.CREATED);
	}

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/current-user/")
	public ResponseEntity<StudentDto> getStudent(Principal principal) {
		Student student = this.studentRepository.findByEmail(principal.getName()).get();
		return new ResponseEntity<>(this.mapper.map(student, StudentDto.class), HttpStatus.OK);
	}

}
