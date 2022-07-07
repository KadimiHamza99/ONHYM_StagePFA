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
	
	@GetMapping("/public/get-all-demandes/am/{username}")
	public ResponseEntity<List<DemandeAccesMessagerie>> getUserDemandesAM(@PathVariable(value = "username") String username){
		return ResponseEntity.ok().body(service.getUserDemandesAM(username));
	}
	@GetMapping("/public/get-all-demandes/si/{username}")
	public ResponseEntity<List<DemandeServiceSi>> getUserDemandesSI(@PathVariable(value = "username") String username){
		return ResponseEntity.ok().body(service.getUserDemandeSI(username));
	}
	
//	@GetMapping("/file/{file}")
//	public ResponseEntity<?> downloadFile(@PathVariable(value = "file") String fileCode){
//		Resource resource = null;
//		
//		try {
//			resource = this.getFileAsResource(fileCode);
//		} catch (IOException e) {
//			return ResponseEntity.internalServerError().build(); 
//		}
//		if(resource==null) {
//			return new ResponseEntity<>("File Not Found",HttpStatus.NOT_FOUND);
//		}
//		String contentType = "application/pdf";
//		String headerValue = "inline; filename=\""+resource.getFilename()+".pdf\"";
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
//								.header(HttpHeaders.CONTENT_DISPOSITION,headerValue)
//								.body(resource);
//	}
//	
//	private Path foundFile;
//	public Resource getFileAsResource(String fileCode) throws IOException {
//		java.nio.file.Path uploadDirectory = Paths.get("demandes");
//		Files.list(uploadDirectory).forEach(f->{
//			if(f.getFileName().toString().equals(fileCode)) {
//				foundFile = f;
//				return;
//			}
//		});
//		if(foundFile!=null) {
//			return new UrlResource(foundFile.toUri());
//		}
//		return null;
//	}
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
