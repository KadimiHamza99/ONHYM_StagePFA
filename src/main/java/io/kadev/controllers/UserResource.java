package io.kadev.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {
	@GetMapping("/demande")
	public String demander() {
		return "FORMULAIRE DE LA DEMANDE";
	}
}
