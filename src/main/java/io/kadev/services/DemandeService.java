package io.kadev.services;

import java.util.List;

import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.DemandeServiceSi;
import io.kadev.models.FormInformationSI;
import io.kadev.models.FormInformationsAM;

public interface DemandeService {
	DemandeAccesMessagerie addDemandeAM(FormInformationsAM formInfo, String username);
	DemandeServiceSi addDemandeSI(FormInformationSI formInfo, String username);
	List<DemandeAccesMessagerie> getAllDemandesAM();
	List<DemandeServiceSi> getAllDemandesSI();
	List<DemandeAccesMessagerie> getUserDemandesAM(String username);
	List<DemandeServiceSi> getUserDemandeSI(String username);
	void managerValidationAM(String idDemande, String managerUsername);
	void dsiValidationAM(String idDemande, String dsiUsername);
	void managerValidationSI(String idDemande, String managerUsername);
	void dsiValidationSI(String idDemande, String dsiUsername);
}
