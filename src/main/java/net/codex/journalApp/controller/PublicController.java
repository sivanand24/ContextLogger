package net.codex.journalApp.controller;

import net.codex.journalApp.entity.User;
import net.codex.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Everything's fine";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
