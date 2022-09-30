package com.tripath.trifood;

import com.tripath.trifood.common.config.AppConstants;
import com.tripath.trifood.entities.Role;
import com.tripath.trifood.api.student.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class TriFoodApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(TriFoodApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			List<Role>  roles = List.of(role, role1);
			List<Role> result = this.roleRepository.saveAll(roles);

			result.forEach( r->{
				System.out.println(r.getName());
			});
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
