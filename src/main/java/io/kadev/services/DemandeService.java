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
	DemandeAccesMessagerie getDemandeAM(String idDemande);
	List<DemandeServiceSi> getAllDemandesSI();
	DemandeServiceSi getDemandeSI(String idDemande);
	List<DemandeAccesMessagerie> getUserDemandesAM(String username);
	List<DemandeServiceSi> getUserDemandeSI(String username);
	void managerValidationAM(String idDemande, String managerUsername);
	void dsiValidationAM(String idDemande, String dsiUsername);
	void managerValidationSI(String idDemande, String managerUsername);
	void dsiValidationSI(String idDemande, String dsiUsername);
	void managerRefusAM(String idDemande,String managerUsername,String messageRefus);
	void managerRefusSI(String idDemande,String managerUsername,String messageRefus);
	void dsiRefusAM(String idDemande,String dsiUsername,String messageRefus);
	void dsiRefusSI(String idDemande,String dsiUsername,String messageRefus);
	byte getEtatDemande(String idDemande);
}
