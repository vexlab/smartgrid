package ml.vexlab.smartgrid.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ml.vexlab.smartgrid.enums.Role;
import ml.vexlab.smartgrid.repository.UserRepository;
import ml.vexlab.smartgrid.service.UserService;
import ml.vexlab.smartgrid.transport.dto.UserDataDTO;

@Component    
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Override
    public void run(String...args) throws Exception {
		if (!userRepository.existsByUsername("admin")) {
			List<Role> roles = new ArrayList<Role>();
			roles.add(Role.ROLE_ADMIN);
			
			userService.signup(new UserDataDTO.Builder()
					.username("admin")
					.password("password")
					.roles(roles)
					.email("admin@vex.io")
					.build());
		}
	}

}
