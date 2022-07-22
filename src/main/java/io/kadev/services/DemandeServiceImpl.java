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

@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

	@Autowired
	private DemandeDAO dao;
	@Autowired 
	private UserServiceImpl userService;
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
		User demandeur = userService.getUser(username);
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
		User demandeur = userService.getUser(username);
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
		return dao.getDemandesAM(userService.getUser(username));
	}

	@Override
	public List<DemandeServiceSi> getUserDemandeSI(String username) {
		return dao.getDemandesSI(userService.getUser(username));
	}

	@Override
	public DemandeAccesMessagerie getDemandeAM(String idDemande) {
		return dao.getDemandeAM(idDemande).orElseThrow();
	}

	@Override
	public DemandeServiceSi getDemandeSI(String idDemande) {
		return dao.getDemandeSI(idDemande).orElseThrow();
	}
	
	@Override
	public void managerValidationAM(String idDemande, String managerUsername) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		User validateur = userService.getUser(managerUsername);
		if (demande.getManager() == null 
				&& demande.getDemandeur().getManager() == validateur && !demande.isRefuser()) {
			demande.setManager(validateur);
			demande.setDateValidationManager(LocalDate.now());
			documentGeneratorService.fillValidationManagerAM(managerUsername, idDemande);
			try {
				emailNotificationService.notify(demande.getDemandeur().getDsi().getEmail(),OBJET_AM,MESSAGE_VALIDATEUR+demande.getDemandeur().getUsername());	
			}catch(Exception e) {
				demande.getDemandeur().getRoles().stream().forEach(role -> {
					if (role.getName().equals("DPI")) {
						demande.setDsi(demande.getDemandeur());
						demande.setDateValidationDsi(LocalDate.now());
						demande.setEtatDemande((byte)1);
						documentGeneratorService.fillValidationDsiAM(demande.getDemandeur().getUsername(),
																		demande.getIdDemandeAccesMessagerie());
						emailNotificationService.notifyWithAttachement(
								demande.getDemandeur().getEmail(),
								OBJET_AM,
								MESSAGE_VALIDATION+demande.getIdDemandeAccesMessagerie(),
								"./demandes/"+demande.getIdDemandeAccesMessagerie()+"DsiValidation.pdf"
						);
					}
				});
			}
			dao.managerValidationDemandeAM(demande);
		}

	}

	@Override
	public void dsiValidationAM(String idDemande, String dsiUsername) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		User validateur = userService.getUser(dsiUsername);
		if (demande.getDsi() == null 
				&& demande.getDemandeur().getDsi() == validateur && !demande.isRefuser()) {
			demande.setDsi(validateur);
			demande.setDateValidationDsi(LocalDate.now());
			documentGeneratorService.fillValidationDsiAM(dsiUsername, idDemande);
			emailNotificationService.notifyWithAttachement(
					demande.getDemandeur().getEmail(),
					OBJET_AM,
					MESSAGE_VALIDATION+demande.getIdDemandeAccesMessagerie(),
					"./demandes/"+demande.getIdDemandeAccesMessagerie()+"DsiValidation.pdf"
					);
			
			dao.dsiValidationDemandeAM(demande);
		}
	}

	@Override
	public void managerValidationSI(String idDemande, String managerUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		User validateur = userService.getUser(managerUsername);
		if (demande.getManager() == null 
				&& demande.getDemandeur().getManager() == validateur && !demande.isRefuser()) {
			demande.setManager(validateur);
			demande.setDateValidationManager(LocalDate.now());
			documentGeneratorService.fillValidationManagerSI(managerUsername, idDemande);
			try {
				emailNotificationService.notify(demande.getDemandeur().getDsi().getEmail(),OBJET_SI,MESSAGE_VALIDATEUR+demande.getDemandeur().getUsername());
			}catch(Exception e) {
				demande.getDemandeur().getRoles().stream().forEach(role -> {
					if (role.getName().equals("DPI")) {
						demande.setDsi(demande.getDemandeur());
						demande.setDateValidationDsi(LocalDate.now());
						demande.setEtatDemande((byte)1);
						documentGeneratorService.fillValidationDsiSI(demande.getDemandeur().getUsername(),
																		demande.getIdDemandeServiceSi());
						emailNotificationService.notifyWithAttachement(
								demande.getDemandeur().getEmail(),
								OBJET_AM,
								MESSAGE_VALIDATION+demande.getIdDemandeServiceSi(),
								"./demandes/"+demande.getIdDemandeServiceSi()+"DsiValidation.pdf"
						);
					}
				});
			}
			dao.managerValidationDemandeSI(demande);
		}
		
	}

	@Override
	public void dsiValidationSI(String idDemande, String dsiUsername) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		User validateur = userService.getUser(dsiUsername);
		if (demande.getDsi() == null 
				&& demande.getDemandeur().getDsi() == validateur && !demande.isRefuser()) {
			demande.setDsi(validateur);
			demande.setDateValidationDsi(LocalDate.now());
			documentGeneratorService.fillValidationDsiSI(dsiUsername, idDemande);
			emailNotificationService.notifyWithAttachement(
					demande.getDemandeur().getEmail(),
					OBJET_AM,
					MESSAGE_VALIDATION+demande.getIdDemandeServiceSi(),
					"./demandes/"+demande.getIdDemandeServiceSi()+"DsiValidation.pdf"
					);
			
			dao.dsiValidationDemandeSI(demande);	
		}
	}

	@Override
	public void managerRefusAM(String idDemande, String managerUsername,String messageRefus) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		User refuseur = userService.getUser(managerUsername);
		if(demande.getManager()==null && demande.getDemandeur().getManager()==refuseur && demande.getEtatDemande()==(byte)-1) {
			dao.managerRefusAM(idDemande, managerUsername);
			emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_AM,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
		}
	}

	@Override
	public void managerRefusSI(String idDemande, String managerUsername,String messageRefus) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		User refuseur = userService.getUser(managerUsername);
		if(demande.getManager()==null && demande.getDemandeur().getManager()==refuseur  && demande.getEtatDemande()==(byte)-1) {
			dao.managerRefusSI(idDemande, managerUsername);
			emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_SI,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
		}
	}	

	@Override
	public void dsiRefusAM(String idDemande, String dsiUsername,String messageRefus) {
		DemandeAccesMessagerie demande = dao.getDemandeAM(idDemande).orElseThrow();
		User refuseur = userService.getUser(dsiUsername);
		if(demande.getDsi()==null && demande.getDemandeur().getDsi()==refuseur && demande.getEtatDemande()!=(byte)1) {
			dao.dsiRefusAM(idDemande, dsiUsername);
			emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_AM,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
		}
	}

	@Override
	public void dsiRefusSI(String idDemande, String dsiUsername,String messageRefus) {
		DemandeServiceSi demande = dao.getDemandeSI(idDemande).orElseThrow();
		User refuseur = userService.getUser(dsiUsername);
		if(demande.getDsi()==null && demande.getDemandeur().getDsi()==refuseur && demande.getEtatDemande()!=(byte)1) {
			dao.dsiRefusSI(idDemande, dsiUsername);
			emailNotificationService.notify(demande.getDemandeur().getEmail(),OBJET_SI,MESSAGE_REFUS+messageRefus+" REFUSER PAR => "+demande.getRefuseur());
		}
	}
	
	@Override
	public byte getEtatDemande(String idDemande) {
		return dao.etatDemande(idDemande);
	}

}
