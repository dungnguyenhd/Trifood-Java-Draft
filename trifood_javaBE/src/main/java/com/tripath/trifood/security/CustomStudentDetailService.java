package com.tripath.trifood.security;

import com.tripath.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.models.Student;
import com.tripath.trifood.repositories.StudentRepository;
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
