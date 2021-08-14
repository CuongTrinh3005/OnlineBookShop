package com.example.onlinebookshop.service.impl;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.OnlinebookshopApplication;
import com.example.onlinebookshop.exception.CustomException;
import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Role;
import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.model.dto.UserDTO;
import com.example.onlinebookshop.model.dto.UserDTOString;
import com.example.onlinebookshop.repository.UserRepository;
import com.example.onlinebookshop.service.EmailService;
import com.example.onlinebookshop.service.RoleService;
import com.example.onlinebookshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	EmailService emailService;

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

	@Override
	public Boolean changePassword(String username, String oldPassword, String newPassword) {
		Boolean isUserExist = userRepository.existsByUsername(username);
		if(!isUserExist)	throw new ResourceNotFoundException("User not found!");
		
		User user = userRepository.getById(username);
		if(!encoder.matches(oldPassword, user.getPassword())){
			throw new CustomException("Current password is not correct!");
		}
		else if(encoder.matches(newPassword, user.getPassword())){
			throw new CustomException("New password must be different with current password!");
		}
		
		user.setPassword(encoder.encode(newPassword));
		userRepository.save(user);
		
		return true;
	}
	
	public String generatePassword(int length){
		byte[] array = new byte[256];
        new Random().nextBytes(array);
  
        String randomString
            = new String(array, Charset.forName("UTF-8"));
  
        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();
  
        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {
  
            char ch = randomString.charAt(k);
  
            if (((ch >= 'a' && ch <= 'z')
                 || (ch >= 'A' && ch <= 'Z')
                 || (ch >= '0' && ch <= '9'))
                && (length > 0)) {
  
                r.append(ch);
                length--;
            }
        }
  
        // return the resultant string
        return r.toString();
    }

	@Override
	public Boolean resetPassword(String username) {
		String newPassword;
		try{
			if(username == null || ("").equals(username))
				throw new CustomException("Username must not empty");
				
			Boolean exist = existsByUsername(username);
			if(!exist)	throw new CustomException("User not found!");
			
			final int passwordLength = 6;
			newPassword = generatePassword(passwordLength);
			
			User user = findByUserName(username).get();

			String contentConfirmation = String.format("Your password is resetted to %s. "
					+ "Keep it secret for safety!!!", newPassword);
			
			String to = user.getEmail();
			String subject = "Reset password";
			String text = contentConfirmation;
			// Sending email
			emailService.sendSimpleMessage(to, subject, text);

			user.setPassword(encoder.encode(newPassword));
			userRepository.save(user);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
}