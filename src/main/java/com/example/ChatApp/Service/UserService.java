package com.example.ChatApp.Service;
import com.example.ChatApp.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ChatApp.Model.*;
import com.example.ChatApp.Repository.UserRepository;

@Service
public class UserService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    
    @Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
   
	public boolean Register( String userName, String email, String password) {
		if(this.userRepository.findByUsername(userName).isEmpty()) {
			String hashedPassString = PasswordHasher(password);
			AppUser user = new AppUser(userName,email,hashedPassString);
			this.userRepository.save(user);
			return true;
		}else {
			return false;
		}
	}
	
	public AppUser getAppUser(String Username) {
		return new AppUser();
	}
	
	public AppUser updateAppUser(AppUser user) {
		if(this.userRepository.existsById(user.getId())) {
			return this.userRepository.save(user);
		}else {
			return null;
		}
	}
	
	public void deleteAppUser(AppUser user) {
		this.userRepository.delete(user);
	}
	
	public boolean login(String UserName, String Password) {
		String HashedPassword = "";
		boolean isMatch = encoder.matches(Password, HashedPassword);
		return isMatch;
	}
	
	public String PasswordHasher(String rawPW) {
	  	        
	        return encoder.encode(rawPW);

	}
}
