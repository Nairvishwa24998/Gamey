package com.shoppingwebsiteinterface.demo.service.implementation;

import com.shoppingwebsiteinterface.demo.dto.CreateUserDTO;
import com.shoppingwebsiteinterface.demo.model.UserInfo;
import com.shoppingwebsiteinterface.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("The User details entered were invalid");
    }

    public UserInfo createUser(CreateUserDTO userDTO) {
        UserInfo savedUser;
        try {
            UserInfo user = new UserInfo();
            user.setUsername(userDTO.getUsername());
//			Encrypting password for security
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(password);
            savedUser = userRepository.save(user);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            String userName = userDTO.getUsername();
            if (userRepository.findByUsername(userName) != null) {
                throw new DataIntegrityViolationException(String.format("A User is already associated with the email - %s. Try logging in if that's yours. Else use a different email id", userName));
            }
           throw new DataIntegrityViolationException("Unable to create User with given details");

        }
    }

}
