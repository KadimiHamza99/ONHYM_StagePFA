package io.kadev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.kadev.models.DemandeAccesMessagerie;
import io.kadev.models.User;

@Repository
public interface DemandeAccesMessagerieRepository extends JpaRepository<DemandeAccesMessagerie, String>{
	List<DemandeAccesMessagerie> findByDemandeur(User demandeur);	
}
