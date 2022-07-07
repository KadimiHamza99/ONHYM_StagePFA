package io.kadev;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
            }
        };
    }
	
	@Bean
	CommandLineRunner start(UserServiceImpl userService,DemandeServiceImpl demandeService,EmailNotifierServiceImpl emailNotificationService) {
		return args -> {
			
//			userService.addRole(new Role(null,"DEMANDEUR"));
//			userService.addRole(new Role(null,"MANAGER"));
//			userService.addRole(new Role(null,"DPI"));
//			
//			User dsi1 = new User(null,"hamzakadimi1999@gmail.com","CHEF DSI 1","1949",new ArrayList<Role>(),null,null);
//			User manager1 = new User(null,"hamza.kadimi@uit.ac.ma","CHEF DIVISION 1","1949",new ArrayList<Role>(),null,null);
//			User demandeur1 = new User(null,"hamzakadimi2019@gmail.com","DEMANDEUR 1","1949",new ArrayList<Role>(),null,null);
//			User dsi2 = new User(null,"hamzakadimi1999@gmail.com","CHEF DSI 2","1949",new ArrayList<Role>(),null,null);
//			User manager2 = new User(null,"hamza.kadimi@uit.ac.ma","CHEF DIVISION 2","1949",new ArrayList<Role>(),null,null);
//			User demandeur2 = new User(null,"hamzakadimi2019@gmail.com","DEMANDEUR 2","1949",new ArrayList<Role>(),null,null);
//			
//			userService.addUser(dsi1);
//			manager1.setDsi(dsi1);
//			userService.addUser(manager1);
//			demandeur1.setManager(manager1);
//			demandeur1.setDsi(dsi1);
//			userService.addUser(demandeur1);
//			
//			userService.addUser(dsi2);
//			manager2.setDsi(dsi2);
//			userService.addUser(manager2);
//			demandeur2.setManager(manager2);
//			demandeur2.setDsi(dsi2);
//			userService.addUser(demandeur2);
//			
//			
//			userService.addRoleToUser("CHEF DSI 1", "DPI");
//			userService.addRoleToUser("CHEF DIVISION 1", "MANAGER");
//			userService.addRoleToUser("CHEF DIVISION 1", "DEMANDEUR");
//			userService.addRoleToUser("DEMANDEUR 1", "DEMANDEUR");
//			
//			userService.addRoleToUser("CHEF DSI 2", "DPI");
//			userService.addRoleToUser("CHEF DIVISION 2", "MANAGER");
//			userService.addRoleToUser("CHEF DIVISION 2", "DEMANDEUR");
//			userService.addRoleToUser("DEMANDEUR 2", "DEMANDEUR");
//			
//			
//			/*
//			 * *Faire les tests necessaires pour le service Demande pour passer 
//			 * à implementer les service Notification et le service GenerateurDocument
//			 */
//			
//			FormInformationsAM am1 = new FormInformationsAM();
//			am1.setAffectation("Affectation CHEF DIVISION 2");
//			am1.setEngagement(true);
//			am1.setImei("IMEI1");
//			am1.setMatricule("MATRICULE1");
//			am1.setNom("NOM1");
//			am1.setPrenom("PRENOM1");
//			am1.setNumeroSerieMobile("NUMEROSERIEMOBILE1");
//			am1.setReferenceMobile("REFERENCEMOBILE1");
//			demandeService.addDemandeAM(am1, "CHEF DIVISION 2");
//			
//			FormInformationSI si = new FormInformationSI();
//			si.setCadrePartenariat("cadre du partenariat");
//			si.setSociete("Societe");
//			si.setEngagement(true);
//			si.setNom("Nom");
//			si.setPrecisionServiceDemande("Precision");
//			si.setPrenom("Prenom");
//			si.setServiceDemande("Autres");
//			demandeService.addDemandeSI(si, "DEMANDEUR 2");

//			demandeService.managerValidationAM("16eafcf9-af81-4077-bab3-b6ca78ae9264", "CHEF DIVISION 2");
//			demandeService.dsiValidationAM("4d8425a1-a368-4d12-882d-598a7db584cb", "CHEF DSI 1");
//			demandeService.dsiRefusAM("16eafcf9-af81-4077-bab3-b6ca78ae9264","CHEF DIVISION 2", "REFUSER");
//			demandeService.managerValidationSI("dce4d1f9-bb87-4a5a-8860-c7d5ab55b7af", "CHEF DIVISION");
//			demandeService.managerRefusAM("ee1817a1-1ab9-43da-9e03-790c52e55d35", "CHEF DIVISION", "NON! impossible");
//			demandeService.dsiRefusSI("dce4d1f9-bb87-4a5a-8860-c7d5ab55b7af", "CHEF DSI","IMPOSSIBLE!!");
//			demandeService.dsiValidationSI("3fd4ceb4-67d2-493f-b102-590d9f816670", "CHEF DSI");
		};
	}

}
