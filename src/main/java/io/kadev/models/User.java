package io.kadev.models;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor	
public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
//	@Column(unique = true)
	private String email;	
	
	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private Collection<Role> roles = Arrays.asList();
	
	@ManyToOne(cascade = CascadeType.MERGE, optional = true)
	@JsonIgnore
	private User manager;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, optional = true)
	private User dsi;
}
