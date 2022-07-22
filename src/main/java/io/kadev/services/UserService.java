package io.kadev.services;

import java.util.List;

import io.kadev.models.Role;
import io.kadev.models.User;
import io.kadev.models.UserForm;

public interface UserService {
	User addUser(User user);
	Role addRole(Role role);
	User getUser(String username);
	User getUserById(Long id);
	Role getRole(String name);
	void addRoleToUser(String username,String name);
	User updateUser(UserForm user);
	List<User> getAllUsers();
	String changePassword(String username,String password);
}
