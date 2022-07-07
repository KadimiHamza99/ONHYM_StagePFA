package io.kadev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demande")
public class DemandeController {
	@GetMapping("/am")
	public String demanderAM() {
		return "demander AM";
	}
	@GetMapping("/si")
	public String demanderSI() {
		return "demander SI";
	}
}
