package com.disneyapp.disneyapp.auth.service;

import com.disneyapp.disneyapp.auth.dto.UserDTO;
import com.disneyapp.disneyapp.auth.entity.UserEntity;
import com.disneyapp.disneyapp.auth.repository.UserRepository;
import com.disneyapp.disneyapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String UserName)throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsername(UserName);
        if (userEntity == null){
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(userEntity.getUsername(),userEntity.getPassword(),
                Collections.emptyList());
    }
    public boolean save(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity = this.userRepository.save(userEntity);
        if (userEntity != null) {
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;

    }
}
