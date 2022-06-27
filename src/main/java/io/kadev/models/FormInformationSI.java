package io.kadev.models;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FormInformationSI {
	private String nom;
	private String prenom;
	private String cadrePartenariat;
	private String serviceDemande;
	@Nullable
	private String precisionServiceDemande;
	private boolean engagement;
}
