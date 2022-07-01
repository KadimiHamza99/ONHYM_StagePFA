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
	@Autowired
	private EmailNotifierServiceImpl emailNotificationService;

	private final String OBJET_AM = "Demande d'accés à la messagerie via le mobile";
	private final String OBJET_SI = "Demande Service SI";
	
	private final String MESSAGE_DEMANDEUR = "Votre Demande est en cours de traitement ,et elle a été transmise à votre chef hiérarchique, l'ID de votre demande est : ";
	private final String MESSAGE_VALIDATEUR = "Vous avez une demande à valider , veuillez consulter votre espace demande , la demande est faite par Mr.";
	
	private final String MESSAGE_VALIDATION = "Votre demande a été VALIDE , l'ID de votre demande est : ";
	
	private final String MESSAGE_REFUS = "Votre demande a été refusé par votre chef hiérarchique à cause de : ";
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
		emailNotificationService.notify(demandeur.getEmail(),OBJET_AM,MESSAGE_DEMANDEUR+demande.getIdDemandeAccesMessagerie());
		emailNotificationService.notify(demandeur.getManager().getEmail(),OBJET_AM,MESSAGE_VALIDATEUR+demandeur.getUsername());
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
		emailNotificationService.notify(demandeur.getEmail(),OBJET_SI,MESSAGE_DEMANDEUR+demande.getIdDemandeServiceSi());
		emailNotificationService.notify(demandeur.getManager().getEmail(),OBJET_SI,MESSAGE_VALIDATEUR+demandeur.getUsername());
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
		User validateur = userRepository.findByUsername(managerUsername);
		if (demande.getManager() == null && demande.getDemandeur().getManager() == validateur) {
			demande.setManager(validateur);
			demande.setDateValidationManager(LocalDate.now());
			documentGeneratorService.fillValidationManagerAM(managerUsername, idDemande);
			emailNotificationService.notify(demande.getDemandeur().getDsi().getEmail(),OBJET_AM,MESSAGE_VALIDATEUR+demande.getDemandeur().getUsername());
			dao.managerValidationDemandeAM(demande);
		}

	}

	@Override
	public void dsiValidationAM(String idDemande, String dsiUsername) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		User validateur = userRepository.findByUsername(dsiUsername);
		if (demande.getDsi() == null && demande.getDemandeur().getDsi() == validateur) {
			demande.setDsi(validateur);
			demande.setDateValidationDsi(LocalDate.now());
			documentGeneratorService.fillValidationDsiAM(dsiUsername, idDemande);
			emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_AM,MESSAGE_VALIDATION+demande.getIdDemandeAccesMessagerie());
			dao.dsiValidationDemandeAM(demande);
		}
	}

	@Override
	public void managerValidationSI(String idDemande, String managerUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		User validateur = userRepository.findByUsername(managerUsername);
		if (demande.getManager() == null && demande.getDemandeur().getManager() == validateur) {
			demande.setManager(validateur);
			demande.setDateValidationManager(LocalDate.now());
			documentGeneratorService.fillValidationManagerSI(managerUsername, idDemande);
			emailNotificationService.notify(demande.getDemandeur().getDsi().getEmail(),OBJET_SI,MESSAGE_VALIDATEUR+demande.getDemandeur().getUsername());
			dao.managerValidationDemandeSI(demande);
		}
		
	}

	@Override
	public void dsiValidationSI(String idDemande, String dsiUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		User validateur = userRepository.findByUsername(dsiUsername);
		if (demande.getDsi() == null && demande.getDemandeur().getDsi() == validateur) {
			demande.setDsi(validateur);
			demande.setDateValidationDsi(LocalDate.now());
			documentGeneratorService.fillValidationDsiSI(dsiUsername, idDemande);
			emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_AM,MESSAGE_VALIDATION+demande.getIdDemandeServiceSi());
			dao.dsiValidationDemandeSI(demande);	
		}
	}

	@Override
	public void managerRefusAM(String idDemande, String managerUsername,String messageRefus) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		dao.managerRefusAM(idDemande, managerUsername);
		emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_AM,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
	}

	@Override
	public void managerRefusSI(String idDemande, String managerUsername,String messageRefus) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		dao.managerRefusSI(idDemande, managerUsername);
		emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_SI,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
	}

	@Override
	public void dsiRefusAM(String idDemande, String dsiUsername,String messageRefus) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		dao.dsiRefusAM(idDemande, dsiUsername);
		emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_AM,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
	}

	@Override
	public void dsiRefusSI(String idDemande, String dsiUsername,String messageRefus) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		dao.dsiRefusSI(idDemande, dsiUsername);
		emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_SI,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
	}

}
