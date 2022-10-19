package com.tripath.trifood.common.security;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.student.dto.Student;
import com.tripath.trifood.api.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CustomStudentDetailService implements UserDetailsService  {
	@Autowired
	private StudentRepository studentRepository;
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = this.studentRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("Student", " email: "+username, 0));
		return student;
	}
}
