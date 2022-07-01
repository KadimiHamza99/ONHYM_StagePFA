package io.kadev;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.kadev.models.FormInformationSI;
import io.kadev.models.FormInformationsAM;
import io.kadev.models.Role;
import io.kadev.models.User;
import io.kadev.services.DemandeServiceImpl;
import io.kadev.services.EmailNotifierServiceImpl;
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
//			userService.addRole(new Role(null,"DEMANDEUR"));
//			userService.addRole(new Role(null,"MANAGER"));
//			userService.addRole(new Role(null,"DPI"));
//			User dsi = new User(null,"hamzakadimi1999@gmail.com","CHEF DSI","1949",new ArrayList<Role>(),null,null);
//			User manager = new User(null,"hamza.kadimi@uit.ac.ma","CHEF DIVISION","1949",new ArrayList<Role>(),null,null);
//			User demandeur = new User(null,"hamzakadimi2019@gmail.com","DEMANDEUR","1949",new ArrayList<Role>(),null,null);
//			userService.addUser(dsi);
//			manager.setDsi(dsi);
//			userService.addUser(manager);
//			demandeur.setManager(manager);
//			demandeur.setDsi(dsi);
//			userService.addUser(demandeur);
//			
//			
//			userService.addRoleToUser("CHEF DSI", "DPI");
//			userService.addRoleToUser("CHEF DSI", "MANAGER");
//			userService.addRoleToUser("CHEF DIVISION", "MANAGER");
//			userService.addRoleToUser("CHEF DIVISION", "DEMANDEUR");
//			userService.addRoleToUser("DEMANDEUR", "DEMANDEUR");
//			
//			
//			/*
//			 * *Faire les tests necessaires pour le service Demande pour passer 
//			 * à implementer les service Notification et le service GenerateurDocument
//			 */
//			FormInformationsAM am = new FormInformationsAM();
//			am.setAffectation("AFFECTATION");
//			am.setEngagement(true);
//			am.setImei("IMEI");
//			am.setMatricule("MATRICULE");
//			am.setNom("NOM");
//			am.setPrenom("PRENOM");
//			am.setNumeroSerieMobile("NUMEROSERIEMOBILE");
//			am.setReferenceMobile("REFERENCEMOBILE");
//			demandeService.addDemandeAM(am, "DEMANDEUR");
//			
//			FormInformationsAM am1 = new FormInformationsAM();
//			am1.setAffectation("AFFECTATION1");
//			am1.setEngagement(true);
//			am1.setImei("IMEI1");
//			am1.setMatricule("MATRICULE1");
//			am1.setNom("NOM1");
//			am1.setPrenom("PRENOM1");
//			am1.setNumeroSerieMobile("NUMEROSERIEMOBILE1");
//			am1.setReferenceMobile("REFERENCEMOBILE1");
//			demandeService.addDemandeAM(am1, "DEMANDEUR");
//			
//			FormInformationSI si = new FormInformationSI();
//			si.setCadrePartenariat("CAAAAAAAAAADRE");
//			si.setSociete("SOCIETEEEEEEEE");
//			si.setEngagement(true);
//			si.setNom("NOMMMMMMMMMM");
//			si.setPrecisionServiceDemande("PREEEEEEEEEECISION");
//			si.setPrenom("PREEEEEEEEEEEEENOM");
//			si.setServiceDemande("Autres");
//			demandeService.addDemandeSI(si, "DEMANDEUR");
//			demandeService.managerValidationAM("96662531-3fb2-4f22-b16a-ca7d6647072b", "CHEF DIVISION");
//			demandeService.dsiValidationAM("96662531-3fb2-4f22-b16a-ca7d6647072b", "CHEF DSI");
//			demandeService.managerValidationSI("dce4d1f9-bb87-4a5a-8860-c7d5ab55b7af", "CHEF DIVISION");
			demandeService.managerRefusSI("9f89022c-a9c4-4276-92c7-3f1d85331809", "CHEF DIVISION", "NON! impossible");
//			demandeService.dsiRefusSI("dce4d1f9-bb87-4a5a-8860-c7d5ab55b7af", "CHEF DSI","IMPOSSIBLE!!");
//			demandeService.dsiValidationSI("3fd4ceb4-67d2-493f-b102-590d9f816670", "CHEF DSI");
			
		};
	}

}
