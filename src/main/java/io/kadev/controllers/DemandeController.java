package io.kadev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.DemandeServiceSi;
import io.kadev.models.FormInformationSI;
import io.kadev.models.FormInformationsAM;
import io.kadev.services.DemandeService;

@RestController
@RequestMapping("/demande")
public class DemandeController {
	
	@Autowired
	DemandeService service;
	
	@PostMapping("/am")
	public ResponseEntity<DemandeAccesMessagerie> demanderAM(
							@RequestParam String username,
							@RequestBody FormInformationsAM formInfo
	) {
		return ResponseEntity.ok().body(service.addDemandeAM(formInfo, username));
	}
	
	@PostMapping("/si")
	public ResponseEntity<DemandeServiceSi> demanderSI(
							@RequestParam String username,
							@RequestBody FormInformationSI formInfo
	) {
		return ResponseEntity.ok().body(service.addDemandeSI(formInfo, username));
	}

}
