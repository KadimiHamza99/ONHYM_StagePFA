package io.kadev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kadev.services.DemandeService;

@RestController
@RequestMapping("/validation")
public class ValidationController {
	
	@Autowired
	private DemandeService service;
	
	@GetMapping("/manager/am")
	public ResponseEntity<?> validerManagerAM(@RequestParam String idDemande,@RequestParam String managerUsername) {
		service.managerValidationAM(idDemande, managerUsername);
		return ResponseEntity.ok().body("Valider");
	}
	@GetMapping("/dpi/am")
	public ResponseEntity<?> validerDpiAM(@RequestParam String idDemande,@RequestParam String dsiUsername) {
		service.dsiValidationAM(idDemande, dsiUsername);
		return ResponseEntity.ok().body("Valider");
	}
	@GetMapping("/manager/si")
	public ResponseEntity<?> validerManagerSI(@RequestParam String idDemande,@RequestParam String managerUsername) {
		service.managerValidationSI(idDemande, managerUsername);
		return ResponseEntity.ok().body("Valider");
	}
	@GetMapping("/dpi/si")
	public ResponseEntity<?> validerDpiSI(@RequestParam String idDemande,@RequestParam String dsiUsername) {
		service.dsiValidationSI(idDemande, dsiUsername);
		return ResponseEntity.ok().body("Valider");
	}
}
