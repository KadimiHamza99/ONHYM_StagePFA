package io.kadev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kadev.services.DemandeService;

@RestController
@RequestMapping("/refus")
public class RefusController {
	
	@Autowired
	private DemandeService service;
	
	@GetMapping("/manager/am")
	public ResponseEntity<?> refuserManagerAM(@RequestParam String idDemande
											,@RequestParam String managerUsername
											,@RequestParam String messageRefus) {
		service.managerRefusAM(idDemande, managerUsername, messageRefus);
		return ResponseEntity.ok().body("Refuser");
	}


	@GetMapping("/dpi/am")
	public ResponseEntity<?> refuserDsiAM(@RequestParam String idDemande
											,@RequestParam String dsiUsername
											,@RequestParam String messageRefus) {
		service.dsiRefusAM(idDemande, dsiUsername, messageRefus);
		return ResponseEntity.ok().body("Refuser");
	}
	
	
	@GetMapping("/manager/si")
	public ResponseEntity<?> refuserManagerSI(@RequestParam String idDemande
			,@RequestParam String managerUsername
			,@RequestParam String messageRefus) {
		service.managerRefusSI(idDemande, managerUsername, messageRefus);
		return ResponseEntity.ok().body("Refuser");
	}
	
	@GetMapping("/dpi/si")
	public ResponseEntity<?> refuserDpiSI(@RequestParam String idDemande
			,@RequestParam String dsiUsername
			,@RequestParam String messageRefus) {
		service.dsiRefusSI(idDemande, dsiUsername, messageRefus);
		return ResponseEntity.ok().body("Refuser");
	}
}
