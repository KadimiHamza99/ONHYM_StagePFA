package io.kadev.models;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "DEMANDE_SERVICE_SI")
public class DemandeServiceSi {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idDemandeServiceSi = UUID.randomUUID().toString();
	private String nom;
	private String prenom;
	private String cadrePartenariat;
	private String serviceDemande;
	@Nullable
	private String precisionServiceDemande;
	private boolean engagement;
	
	@JoinColumn(name = "demandeur",referencedColumnName="userId")
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private User demandeur;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateDemande;
	
	private byte etatDemande = -1;
	
	@JoinColumn(name = "manager",referencedColumnName="userId")
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	private User manager;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateValidationManager;
	
	@JoinColumn(name = "dsi",referencedColumnName="userId")
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	private User dsi;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateValidationDsi;
	
}
