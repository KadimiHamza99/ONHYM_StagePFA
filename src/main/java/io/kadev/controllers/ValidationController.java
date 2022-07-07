package io.kadev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class ValidationController {
	@GetMapping("/manager/am")
	public String validerManagerAM() {
		return "Manager valider AM";
	}
	@GetMapping("/dpi/am")
	public String validerDpiAM() {
		return "Dpi valider AM";
	}
	@GetMapping("/manager/si")
	public String validerManagerSI() {
		return "Manager valider SI";
	}
	@GetMapping("/dpi/si")
	public String validerDpiSI() {
		return "Dpi valider SI";
	}
}
