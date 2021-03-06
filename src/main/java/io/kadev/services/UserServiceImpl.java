package io.kadev.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itextpdf.text.log.SysoCounter;

import io.kadev.models.Role;
import io.kadev.models.User;
import io.kadev.models.UserForm;
import io.kadev.repositories.RoleRepository;
import io.kadev.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			log.error("Username not found !");
			throw new UsernameNotFoundException("User not found in the database !");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(
			user.getUsername(),user.getPassword(),authorities
		);
	}
	
	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Role getRole(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public void addRoleToUser(String username, String name) {
		log.info("Adding role {} to the user {}",name,username);
		User user = getUser(username);
		Role role = getRole(name);
		user.getRoles().add(role);
	}

	@Override
	public User addUser(User user) {
		log.info("Adding new user");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role addRole(Role role) {
		log.info("Adding new role");
		return roleRepository.save(role);
	}

	@Override
	public User updateUser(UserForm userUpdate) {
		User user = userRepository.findByUsername(userUpdate.getUsername());
		user.setDsi(this.getUser(userUpdate.getDsi()));
		user.setEmail(userUpdate.getEmail());
		user.setManager(this.getUser(userUpdate.getManager()));
		user.setState(userUpdate.getState());
		user.setStateDate(userUpdate.getStateDate());
		user.getRoles().removeAll(user.getRoles());
		if(userUpdate.getRoles()!=null) {
			userUpdate.getRoles().forEach(role->{
				this.addRoleToUser(userUpdate.getUsername(), role);
			});
		}
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public String changePassword(String username, String password) {
		User user = userRepository.findByUsername(username);
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
		userRepository.save(user);
		return "Le mot de passe est chang?? !";
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow();
	}
	
}
