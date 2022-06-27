package io.kadev.services;

import java.util.List;

import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.DemandeServiceSi;
import io.kadev.models.User;

public interface DemandeService {
	DemandeAccesMessagerie addDemandeAM(DemandeAccesMessagerie demande);
	DemandeServiceSi addDemandeSI(DemandeServiceSi demande);
	List<DemandeAccesMessagerie> getAllDemandesAM();
	List<DemandeServiceSi> getAllDemandesSI();
	List<DemandeAccesMessagerie> getUserDemandesAM(User demandeur);
	List<DemandeServiceSi> getUserDemandeSI(User demandeur);
	void managerValidationAM(DemandeAccesMessagerie demande);
	void dsiValidationAM(DemandeAccesMessagerie demande);
	void managerValidationSI(DemandeServiceSi demande);
	void dsiValidationSI(DemandeServiceSi demande);
}
