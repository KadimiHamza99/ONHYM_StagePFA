package io.kadev;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.kadev.dao.DemandeDAO;
import io.kadev.models.Role;
import io.kadev.models.User;
import io.kadev.services.DemandeServiceImpl;
import io.kadev.services.UserServiceImpl;

@SpringBootApplication
public class OnhymStagePfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnhymStagePfaApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner start(UserServiceImpl userService,DemandeServiceImpl demandeService) {
		return args -> {
			userService.addRole(new Role(null,"DEMANDEUR"));
			userService.addRole(new Role(null,"MANAGER"));
			userService.addRole(new Role(null,"DPI"));
			
			userService.addUser(new User(null,"hamzakadimi1999@gmail.com","CHEF DSI","1949",new ArrayList<Role>()));
			userService.addUser(new User(null,"hamza.kadimi@uit.ac.ma","CHEF DIVISION","1949",new ArrayList<Role>()));
			userService.addUser(new User(null,"hamza.kadimi@uit.ac.ma","DEMANDEUR","1949",new ArrayList<Role>()));
			
			userService.addRoleToUser("CHEF DSI", "DPI");
			userService.addRoleToUser("CHEF DSI", "MANAGER");
			userService.addRoleToUser("CHEF DIVISION", "MANAGER");
			userService.addRoleToUser("CHEF DIVISION", "DEMANDEUR");
			userService.addRoleToUser("DEMANDEUR", "DEMANDEUR");
			
			
			/*
			 * *Faire les tests necessaires pour le service Demande pour passer 
			 * à implementer les service Notification et le service GenerateurDocument
			 */
		};
	}

}
