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
	 */

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
		demandeAMRepository.findById(demande.getIdDemandeAccesMessagerie()).ifPresent(d -> {
			d.setEtatDemande((byte) 1);
			demandeAMRepository.save(d);
		});
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

	public void managerRefusAM(String idDemande, String managerUsername) {
		demandeAMRepository.findById(idDemande).ifPresent(d -> {
			if (d.getDsi() == null) {
				d.setManager(null);
				d.setDsi(null);
				d.setDateValidationDsi(null);
				d.setDateValidationManager(null);
				d.setEtatDemande((byte) -1);
				d.setRefuser(true);
				d.setRefuseur(managerUsername);
				demandeAMRepository.save(d);
			}
		});
	}

	public void managerRefusSI(String idDemande, String managerUsername) {
		demandeSIRepository.findById(idDemande).ifPresent(d -> {
			if (d.getDsi() == null) {
				d.setManager(null);
				d.setDsi(null);
				d.setDateValidationDsi(null);
				d.setDateValidationManager(null);
				d.setEtatDemande((byte) -1);
				d.setRefuser(true);
				d.setRefuseur(managerUsername);
				demandeSIRepository.save(d);
			}

		});
	}

	public void dsiRefusAM(String idDemande, String dsiUsername) {
		demandeAMRepository.findById(idDemande).ifPresent(d -> {
			if (d.getDsi() == null) {
				d.setManager(null);
				d.setDsi(null);
				d.setDateValidationDsi(null);
				d.setDateValidationManager(null);
				d.setEtatDemande((byte) -1);
				d.setRefuser(true);
				d.setRefuseur(dsiUsername);
				demandeAMRepository.save(d);
			}
		});
	}

	public void dsiRefusSI(String idDemande, String dsiUsername) {
		demandeSIRepository.findById(idDemande).ifPresent(d -> {
			if (d.getDsi() == null) {
				d.setManager(null);
				d.setDsi(null);
				d.setDateValidationDsi(null);
				d.setDateValidationManager(null);
				d.setEtatDemande((byte) -1);
				d.setRefuser(true);
				d.setRefuseur(dsiUsername);
				demandeSIRepository.save(d);
			}
		});
	}
	
	public byte etatDemande(String idDemande) {
		if(demandeSIRepository.findById(idDemande).isPresent()) {
			DemandeServiceSi demande = demandeSIRepository.findById(idDemande).orElseThrow();
			return demande.getEtatDemande();
		}else if(demandeAMRepository.findById(idDemande).isPresent()) {
			DemandeAccesMessagerie demande = demandeAMRepository.findById(idDemande).orElseThrow();
			return demande.getEtatDemande();
		}
		return Byte.MAX_VALUE;
	}

}
