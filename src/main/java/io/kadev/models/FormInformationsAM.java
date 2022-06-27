package io.kadev.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FormInformationsAM {
	private String nom;
	private String prenom;
	private String matricule;
	private String affectation;
	private String referenceMobile;
	private String numeroSerieMobile;
	private String imei;
	private boolean engagement;
}
