package net.codex.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.codex.journalApp.entity.User;
import net.codex.journalApp.service.UserDetailsServiceImpl;
import net.codex.journalApp.service.UserService;
import net.codex.journalApp.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Everything's fine";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
    try{
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    } catch(Exception e){
        log.error("Exception is occurred while createAuthenticationToken",e);
        return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);
    }
}
}
