package io.kadev.models;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserForm {
	
	private String username;
	private String email;
	private List<String> roles;
	private String manager;
	private String dsi;
	private byte state;
	private LocalDate stateDate;
	
}
