package io.kadev.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kadev.dao.DemandeDAO;
import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.DemandeServiceSi;
import io.kadev.models.FormInformationSI;
import io.kadev.models.FormInformationsAM;
import io.kadev.models.User;
import io.kadev.repositories.UserRepository;

@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

	@Autowired
	private DemandeDAO dao;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DocumentsGeneratorServiceImpl documentGeneratorService;

	@Override
	public DemandeAccesMessagerie addDemandeAM(FormInformationsAM formInfo, String username) {
		DemandeAccesMessagerie demande = new DemandeAccesMessagerie();
		User demandeur = userRepository.findByUsername(username);
		demandeur.getRoles().stream().forEach(role -> {
			if (role.getName().equals("MANAGER")) {
				demande.setEtatDemande((byte) 0);
				this.managerValidationAM(demande.getIdDemandeAccesMessagerie(), username);
			}
		});
		demande.setNom(formInfo.getNom());
		demande.setPrenom(formInfo.getPrenom());
		demande.setMatricule(formInfo.getMatricule());
		demande.setAffectation(formInfo.getAffectation());
		demande.setReferenceMobile(formInfo.getReferenceMobile());
		demande.setNumeroSerieMobile(formInfo.getNumeroSerieMobile());
		demande.setImei(formInfo.getImei());
		demande.setEngagement(formInfo.isEngagement());
		demande.setDemandeur(demandeur);
		demande.setDateDemande(LocalDate.now());
		documentGeneratorService.fillDemandeAccesMessagerie(formInfo, demande.getIdDemandeAccesMessagerie());
		return dao.addDemandeAM(demande);
	}

	@Override
	public DemandeServiceSi addDemandeSI(FormInformationSI formInfo, String username) {
		DemandeServiceSi demande = new DemandeServiceSi();
		User demandeur = userRepository.findByUsername(username);
		demandeur.getRoles().stream().forEach(role -> {
			if (role.getName().equals("MANAGER")) {
				demande.setEtatDemande((byte) 0);
				this.managerValidationSI(demande.getIdDemandeServiceSi(), username);
			}
		});
		demande.setNom(formInfo.getNom());
		demande.setPrenom(formInfo.getPrenom());
		demande.setSociete(formInfo.getSociete());
		demande.setCadrePartenariat(formInfo.getCadrePartenariat());
		demande.setServiceDemande(formInfo.getServiceDemande());
		demande.setPrecisionServiceDemande(formInfo.getPrecisionServiceDemande());
		demande.setEngagement(formInfo.isEngagement());
		demande.setDemandeur(demandeur);
		demande.setDateDemande(LocalDate.now());
		documentGeneratorService.fillDemandeServiceSI(formInfo, demande.getIdDemandeServiceSi());
		return dao.addDemandeSI(demande);
	}

	@Override
	public List<DemandeAccesMessagerie> getAllDemandesAM() {
		return dao.getAllDemandesAM();
	}

	@Override
	public List<DemandeServiceSi> getAllDemandesSI() {
		return dao.getAllDemandesSI();
	}

	@Override
	public List<DemandeAccesMessagerie> getUserDemandesAM(String username) {
		return dao.getDemandesAM(userRepository.findByUsername(username));
	}

	@Override
	public List<DemandeServiceSi> getUserDemandeSI(String username) {
		return dao.getDemandesSI(userRepository.findByUsername(username));
	}

	@Override
	public void managerValidationAM(String idDemande, String managerUsername) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		if (demande.getManager() == null) {
			User validateur = userRepository.findByUsername(managerUsername);
			demande.setManager(validateur);
			demande.setDateValidationManager(LocalDate.now());
			documentGeneratorService.fillValidationManagerAM(managerUsername, idDemande);
			dao.managerValidationDemandeAM(demande);
		}

	}

	@Override
	public void dsiValidationAM(String idDemande, String dsiUsername) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		if (demande.getManager() == null) {
			demande.setDsi(userRepository.findByUsername(dsiUsername));
			demande.setDateValidationDsi(LocalDate.now());
			documentGeneratorService.fillValidationDsiAM(dsiUsername, idDemande);
			dao.dsiValidationDemandeAM(demande);

		}
	}

	@Override
	public void managerValidationSI(String idDemande, String managerUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		if (demande.getManager() == null) {
			demande.setManager(userRepository.findByUsername(managerUsername));
			demande.setDateValidationManager(LocalDate.now());
			documentGeneratorService.fillValidationManagerSI(managerUsername, idDemande);
			dao.managerValidationDemandeSI(demande);
		}
		
	}

	@Override
	public void dsiValidationSI(String idDemande, String dsiUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		if (demande.getManager() == null) {
			demande.setDsi(userRepository.findByUsername(dsiUsername));
			demande.setDateValidationDsi(LocalDate.now());
			documentGeneratorService.fillValidationDsiSI(dsiUsername, idDemande);
			dao.dsiValidationDemandeSI(demande);	
		}
	}

	@Override
	public void managerRefusAM(String idDemande, String managerUsername) {
		dao.managerRefusAM(idDemande, managerUsername);
	}

	@Override
	public void managerRefusSI(String idDemande, String managerUsername) {
		dao.managerRefusSI(idDemande, managerUsername);
	}

	@Override
	public void dsiRefusAM(String idDemande, String dsiUsername) {
		dao.dsiRefusAM(idDemande, dsiUsername);
	}

	@Override
	public void dsiRefusSI(String idDemande, String dsiUsername) {
		dao.dsiRefusSI(idDemande, dsiUsername);
	}

}
