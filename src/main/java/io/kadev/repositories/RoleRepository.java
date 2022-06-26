package io.kadev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.kadev.models.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
