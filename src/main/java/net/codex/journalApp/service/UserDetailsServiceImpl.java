package net.codex.journalApp.service;

import net.codex.journalApp.repository.UserRepository;
import net.codex.journalApp.entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



    @Component
    public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(username);
            if(user != null){
                return org.springframework.security.core.userdetails.User.builder()
                       .username(user.getUserName())
                       .password(user.getPassword())
                       .roles(user.getRoles().toArray(new String[0]))
                       .build();
            }
            throw new UsernameNotFoundException("User not found with username: " + username);




        }
    }

