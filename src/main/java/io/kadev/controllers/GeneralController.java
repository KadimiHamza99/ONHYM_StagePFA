package io.kadev.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.DemandeServiceSi;
import io.kadev.services.DemandeService;
import io.kadev.services.FileService;

@RestController
public class GeneralController {
	@Autowired
	private DemandeService service;
	@Autowired
	private FileService fileService;
	
	@GetMapping("/private/get-all-demandes/am")
	public ResponseEntity<List<DemandeAccesMessagerie>> getAllDemandesAM(){
		return ResponseEntity.ok().body(service.getAllDemandesAM());
	}
	@GetMapping("/private/get-all-demandes/si")
	public ResponseEntity<List<DemandeServiceSi>> getAllDemandesSI(){
		return ResponseEntity.ok().body(service.getAllDemandesSI());
	}
	
	@GetMapping("/public/get-demande/am/{id}")
	public ResponseEntity<DemandeAccesMessagerie> getDemandeAM(@PathVariable(value = "id") String idDemande){
		return ResponseEntity.ok().body(service.getDemandeAM(idDemande));
	}
	@GetMapping("/public/get-demande/si/{id}")
	public ResponseEntity<DemandeServiceSi> getDemandeSI(@PathVariable(value = "id") String idDemande){
		return ResponseEntity.ok().body(service.getDemandeSI(idDemande));
	}
	
	@GetMapping("/public/get-all-demandes/am")
	public ResponseEntity<List<DemandeAccesMessagerie>> getUserDemandesAM(@RequestParam String username){
		return ResponseEntity.ok().body(service.getUserDemandesAM(username));
	}
	@GetMapping("/public/get-all-demandes/si")
	public ResponseEntity<List<DemandeServiceSi>> getUserDemandesSI(@RequestParam String username){
		return ResponseEntity.ok().body(service.getUserDemandeSI(username));
	}
	
	@GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Resource file = fileService.download(filename);
        Path path = file.getFile()
                        .toPath();

        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                             .body(file);
    }

	
}
