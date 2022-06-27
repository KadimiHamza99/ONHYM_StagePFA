package io.kadev;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.kadev.dao.DemandeDAO;
import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.Role;
import io.kadev.models.User;
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
	CommandLineRunner start(UserServiceImpl userService,DemandeDAO demandeDAO) {
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
			
			DemandeAccesMessagerie demande1 = new DemandeAccesMessagerie();
			User user = new User(null,"test@gmail.com","testeur","1949",new ArrayList<Role>());
			userService.addUser(user);
			demande1.setDemandeur(user);
			demandeDAO.addDemandeAM(demande1);
			demandeDAO.managerValidationDemandeAM(demande1);
			demandeDAO.dsiValidationDemandeAM(demande1);
			DemandeAccesMessagerie demande2 = new DemandeAccesMessagerie();
			demande2.setDemandeur(user);
			demandeDAO.addDemandeAM(demande2);
			demandeDAO.managerValidationDemandeAM(demande2);
			DemandeAccesMessagerie demande3 = new DemandeAccesMessagerie();
			demande3.setDemandeur(new User());
			demandeDAO.addDemandeAM(demande3);
			demandeDAO.managerValidationDemandeAM(demande3);
			List<DemandeAccesMessagerie> demandes = demandeDAO.getDemandesAM(user);
			System.out.println("___________________________TEST____________________________");
			demandes.stream().forEach(demande->System.out.println(demande.toString()));
		};
	}

}
