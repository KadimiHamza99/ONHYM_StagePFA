package io.kadev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
//			userService.addRole(new Role(null,"DSI"));
//			userService.addRole(new Role(null,"ADMIN"));
//			User admin = new User("hamzakadimi1999@gmail.com","ADMIN","ADMIN");
//			userService.addUser(admin);
//			userService.addRoleToUser("ADMIN", "ADMIN");

		};
	}

}
