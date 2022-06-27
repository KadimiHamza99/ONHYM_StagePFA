package io.kadev.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.DemandeServiceSi;
import io.kadev.models.User;
import io.kadev.repositories.DemandeAccesMessagerieRepository;
import io.kadev.repositories.DemandeServiceSiRepository;

@Component
public class DemandeDAO {

	@Autowired
	private DemandeAccesMessagerieRepository demandeAMRepository;
	@Autowired
	private DemandeServiceSiRepository demandeSIRepository;
	
	/*
	 * Get une demande 
	 * */

	public Optional<DemandeAccesMessagerie> getDemandeAM(String id) {
		return demandeAMRepository.findById(id);
	}
	
	public Optional<DemandeServiceSi> getDemandeSI(String id) {
		return demandeSIRepository.findById(id);
	}
	
	/*
	 * Faire une demande acces mesagerie ou service SI
	 */
	public DemandeAccesMessagerie addDemandeAM(DemandeAccesMessagerie demande) {
		return demandeAMRepository.save(demande);
	}

	public DemandeServiceSi addDemandeSI(DemandeServiceSi demande) {
		return demandeSIRepository.save(demande);
	}

	/*
	 * Retourner les demandes dans la base de données
	 */

	public List<DemandeAccesMessagerie> getAllDemandesAM() {
		return demandeAMRepository.findAll();
	}

	public List<DemandeServiceSi> getAllDemandesSI() {
		return demandeSIRepository.findAll();
	}

	/*
	 * Retourner les demandes selon un demandeur precis
	 */

	public List<DemandeAccesMessagerie> getDemandesAM(User demandeur) {
		return demandeAMRepository.findByDemandeur(demandeur);
	}

	public List<DemandeServiceSi> getDemandesSI(User demandeur) {
		return demandeSIRepository.findByDemandeur(demandeur);
	}

	/*
	 * Validation des demandes suivant une hierarchie du chef de division au chef
	 * dsi
	 */

	public void managerValidationDemandeAM(DemandeAccesMessagerie demande) {
		if (demande.getEtatDemande() == -1) {
			demandeAMRepository.findById(demande.getIdDemandeAccesMessagerie()).ifPresent(d -> {
				d.setEtatDemande((byte) 0);
				demandeAMRepository.save(d);
			});
		}
	}

	public void dsiValidationDemandeAM(DemandeAccesMessagerie demande) {
//		if(demande.getEtatDemande()== (byte)  {
		demandeAMRepository.findById(demande.getIdDemandeAccesMessagerie()).ifPresent(d -> {
			d.setEtatDemande((byte) 1);
			demandeAMRepository.save(d);
		});
//		}
	}

	public void managerValidationDemandeSI(DemandeServiceSi demande) {
		demandeSIRepository.findById(demande.getIdDemandeServiceSi()).ifPresent(d -> {
			d.setEtatDemande((byte) 0);
			demandeSIRepository.save(d);
		});
	}

	public void dsiValidationDemandeSI(DemandeServiceSi demande) {
		demandeSIRepository.findById(demande.getIdDemandeServiceSi()).ifPresent(d -> {
			d.setEtatDemande((byte) 1);
			demandeSIRepository.save(d);
		});
	}
}
