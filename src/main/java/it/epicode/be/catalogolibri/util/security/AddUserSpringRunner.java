package it.epicode.be.catalogolibri.util.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.be.catalogolibri.model.security.Role;
import it.epicode.be.catalogolibri.model.security.Roles;
import it.epicode.be.catalogolibri.model.security.User;
import it.epicode.be.catalogolibri.repository.security.RoleRepository;
import it.epicode.be.catalogolibri.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		
		Role roleU = new Role();
		roleU.setRoleName(Roles.ROLE_USER);
		
		Role role = new Role();
		role.setRoleName(Roles.ROLE_ADMIN);
		User user = new User();
		Set<Role> roles = new HashSet<>(); 
		roles.add(role);
		user.setUserName("admin");
		user.setPassword(bCrypt.encode("admin"));
		user.setEmail("admin@domain.com");
		user.setRoles(roles);
		user.setActive(true);
		
		User user1 = new User();
		Set<Role> roles1 = new HashSet<>(); 
		roles1.add(roleU);
		user1.setUserName("user");
		user1.setPassword(bCrypt.encode("user"));
		user1.setEmail("user@domain.com");
		user1.setRoles(roles1);
		user1.setActive(true);
		
		roleRepository.save(role);
		roleRepository.save(roleU);
		
		userRepository.save(user);
		userRepository.save(user1);
		
		log.info("User 1 : " + user.toString());
		log.info("User 1 : " + user1.toString());

	}

}
