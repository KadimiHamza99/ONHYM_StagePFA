package io.kadev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.kadev.models.DemandeServiceSi;
import io.kadev.models.User;

@Repository
public interface DemandeServiceSiRepository extends JpaRepository<DemandeServiceSi, String>{
	List<DemandeServiceSi> findByDemandeur(User demandeur);
}
