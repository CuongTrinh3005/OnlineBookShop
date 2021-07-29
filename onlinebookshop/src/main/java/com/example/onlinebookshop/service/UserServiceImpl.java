package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.OnlinebookshopApplication;
import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Role;
import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.model.dto.UserDTO;
import com.example.onlinebookshop.model.dto.UserDTOString;
import com.example.onlinebookshop.repository.UserRepository;
import com.example.onlinebookshop.service.impl.RoleService;
import com.example.onlinebookshop.service.impl.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByUserName(String userName) throws UsernameNotFoundException{
		User user = userRepository.findByUsername(userName)
				.orElseThrow(()-> new ResourceNotFoundException(userName + " not found"));
		return Optional.of(user);				
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User convertUserDtoToUser(UserDTO userDTO) {
		if(userDTO == null)	throw new ResourceNotFoundException("Do not have user's data"); 
		
		User user = new User();
		user.setUserName(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setFullName(userDTO.getFullName());
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setGender(userDTO.getGender());
		user.setPhoto(userDTO.getPhoto());
		
		String[] roleIds = userDTO.getRoleIds();
		List<Integer> listRoleIds = OnlinebookshopApplication.convertStringArrToIntArr(roleIds);
		List<Role> listRoles = roleService.findAllById(listRoleIds);
		user.setRoles(listRoles.stream().collect(Collectors.toSet()));
		
		return user;
	}
	
	@Override
	public UserDTOString convertUserToUserDTOString(User user) {
		if(user == null)	throw new ResourceNotFoundException("Do not have user's data");
		
		UserDTOString userdstr = new UserDTOString();
		userdstr.setUsername(user.getUserName());
		userdstr.setFullName(user.getFullName());
		userdstr.setEmail(user.getEmail());
		userdstr.setAddress(user.getAddress());
		userdstr.setPhoneNumber(user.getPhoneNumber());
		userdstr.setGender(user.getGender());
		userdstr.setPhoto(user.getPhoto());
		
		String roles = "";
		Object[] roleArray = user.getRoles().toArray();
		for(int index=0; index<roleArray.length; index++){
			Role role = (Role) roleArray[index];
			String roleName = "";
			if(role.getRoleId() == 1){
				roleName += "User";
			}
			else if(role.getRoleId() == 2){
				roleName += "Admin";
			}
			roles += roleName + " ";
		}
		   
		userdstr.setRoles(roles);
		return userdstr;
	}

	@Override
	public User updateUser(UserDTO userDTO, String username) {
		Optional<User> userOpt = findByUserName(username);

		User existedUser = userOpt.get();
		
		User user = convertUserDtoToUser(userDTO);
		
		existedUser.setUserName(user.getUserName());
		existedUser.setFullName(user.getFullName());
		existedUser.setGender(user.getGender());
		existedUser.setPhoneNumber(user.getPhoneNumber());
		existedUser.setAddress(user.getAddress());
		existedUser.setPhoto(user.getPhoto());
		existedUser.setRoles(user.getRoles());
		
		return saveUser(existedUser);
	}

	@Override
	public User createNewUser(UserDTO userDTO) {
		User user = convertUserDtoToUser(userDTO);
		String password = user.getPassword();
		String encodedPassword = encoder.encode(password);
		user.setPassword(encodedPassword);
		
		return userRepository.save(user);
	}
}