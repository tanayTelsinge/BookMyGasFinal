package com.app.bookmygas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.User;
import com.app.bookmygas.repository.UserRepository;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder enc;

    public User createUser(User user) {
    	if(userRepository.findByEmail(user.getEmail()) != null){
    		throw new RuntimeException("User with email " + user.getEmail() + " already exists");
    	}
    	user.setPassword(enc.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Integer id, User user) {
        if (userRepository.existsById(id)) {
            user.setUserId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
}
