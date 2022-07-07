package io.kadev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refus")
public class RefusController {
	@GetMapping("/manager/am")
	public String refuserManagerAM() {
		return "manager refuser AM";
	}
	@GetMapping("/dpi/am")
	public String refuserDpiAM() {
		return "Dpi refuser AM";
	}
	@GetMapping("/manager/si")
	public String refuserManagerSI() {
		return "manager refuser SI";
	}
	@GetMapping("/dpi/si")
	public String refuserDpiSI() {
		return "Dpi refuser SI";
	}
}
