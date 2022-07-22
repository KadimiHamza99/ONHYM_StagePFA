package io.kadev.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.kadev.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	
	@Autowired
	@Transient
	@JsonIgnore
	UserServiceImpl userService;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
//	@Column(unique = true)
	private String email;	
	
	private LocalDate createdDate = LocalDate.now();
	
	@Column(unique = true)
	private String username;
//	@JsonIgnore
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
//	@JsonIgnore
	private List<Role> roles = new ArrayList<Role>();
	
	@ManyToOne(cascade = CascadeType.MERGE, optional = true)
//	@JsonIgnore
	private User manager;
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, optional = true)
	private User dsi;
	
	private byte state = 1;
	private LocalDate stateDate = LocalDate.now();
	
	public User(String email,String username,String password) {
		this.email=email;
		this.username=username;
		this.password = password;
	}
	
}
