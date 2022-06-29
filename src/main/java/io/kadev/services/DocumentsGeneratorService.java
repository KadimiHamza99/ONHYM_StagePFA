package io.kadev.services;

import io.kadev.models.FormInformationSI;
import io.kadev.models.FormInformationsAM;

public interface DocumentsGeneratorService {
	void fillDemandeAccesMessagerie(FormInformationsAM form,String idDemande);
	void fillDemandeServiceSI(FormInformationSI form,String idDemande);
	void fillValidationManagerAM(String nom,String idDemande);
	void fillValidationDsiAM(String nom,String idDemande);
	void fillValidationManagerSI(String nom,String idDemande);
	void fillValidationDsiSI(String nom,String idDemande);
}
