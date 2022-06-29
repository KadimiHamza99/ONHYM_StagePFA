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
//			
//			userService.addUser(new User(null,"hamzakadimi1999@gmail.com","CHEF DSI","1949",new ArrayList<Role>()));
//			userService.addUser(new User(null,"hamza.kadimi@uit.ac.ma","CHEF DIVISION","1949",new ArrayList<Role>()));
//			userService.addUser(new User(null,"hamza.kadimi@uit.ac.ma","DEMANDEUR","1949",new ArrayList<Role>()));
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
//			demandeService.managerValidationAM("4f9e1430-3091-494d-a284-deb0e2f550e7", "CHEF DIVISION");
//			demandeService.dsiValidationAM("4f9e1430-3091-494d-a284-deb0e2f550e7", "CHEF DSI");
			demandeService.managerValidationSI("318b9827-e875-44f1-9691-81553cc9014b", "CHEF DIVISION");
			demandeService.dsiRefusSI("318b9827-e875-44f1-9691-81553cc9014b", "CHEF DSI");
//			demandeService.dsiValidationSI("3fd4ceb4-67d2-493f-b102-590d9f816670", "CHEF DSI");
			
			
		};
	}

}
