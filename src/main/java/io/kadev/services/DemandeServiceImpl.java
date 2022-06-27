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
import io.kadev.repositories.UserRepository;

@Service @Transactional
public class DemandeServiceImpl implements DemandeService{
	
	@Autowired
	private DemandeDAO dao;
	@Autowired
	private UserRepository userRepository;

	@Override
	public DemandeAccesMessagerie addDemandeAM(FormInformationsAM formInfo, String username) {
		DemandeAccesMessagerie demande = new DemandeAccesMessagerie();
		demande.setNom(formInfo.getNom());
		demande.setPrenom(formInfo.getPrenom());
		demande.setMatricule(formInfo.getMatricule());
		demande.setAffectation(formInfo.getAffectation());
		demande.setReferenceMobile(formInfo.getReferenceMobile());
		demande.setNumeroSerieMobile(formInfo.getNumeroSerieMobile());
		demande.setImei(formInfo.getImei());
		demande.setEngagement(formInfo.isEngagement());
		demande.setDemandeur(userRepository.findByUsername(username));
		demande.setDateDemande(LocalDate.now());
		return dao.addDemandeAM(demande);
	}

	@Override
	public DemandeServiceSi addDemandeSI(FormInformationSI formInfo, String username) {
		DemandeServiceSi demande = new DemandeServiceSi();
		demande.setNom(formInfo.getNom());
		demande.setPrenom(formInfo.getPrenom());
		demande.setCadrePartenariat(formInfo.getCadrePartenariat());
		demande.setServiceDemande(formInfo.getServiceDemande());
		demande.setPrecisionServiceDemande(formInfo.getPrecisionServiceDemande());
		demande.setEngagement(formInfo.isEngagement());
		demande.setDemandeur(userRepository.findByUsername(username));
		demande.setDateDemande(LocalDate.now());
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
		demande.setManager(userRepository.findByUsername(managerUsername));
		demande.setDateValidationManager(LocalDate.now());
		dao.managerValidationDemandeAM(demande);
	}

	@Override
	public void dsiValidationAM(String idDemande, String dsiUsername) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		demande.setDsi(userRepository.findByUsername(dsiUsername));
		demande.setDateValidationDsi(LocalDate.now());
		dao.dsiValidationDemandeAM(demande);
	}

	@Override
	public void managerValidationSI(String idDemande, String managerUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		demande.setManager(userRepository.findByUsername(managerUsername));
		demande.setDateValidationManager(LocalDate.now());
		dao.managerValidationDemandeSI(demande);
	}

	@Override
	public void dsiValidationSI(String idDemande, String dsiUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		demande.setDsi(userRepository.findByUsername(dsiUsername));
		demande.setDateValidationDsi(LocalDate.now());
		dao.dsiValidationDemandeSI(demande);
	}

	

}
