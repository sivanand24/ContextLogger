package net.codex.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.codex.journalApp.api.response.WeatherResponse;
import net.codex.journalApp.entity.User;

import net.codex.journalApp.repository.UserRepository;
import net.codex.journalApp.service.UserService;
import net.codex.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@Tag(name = "USER APIs" , description = "user related REST APIs")
@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = getAuthentication(authentication).getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private static Authentication getAuthentication(Authentication authentication) {
        return authentication;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse= weatherService.getWeather("Chennai");
        String greeting = "";
        if(weatherResponse != null){
            greeting =  ", weather feels today " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("hii "+authentication.getName() + greeting,HttpStatus.OK);
    }
}