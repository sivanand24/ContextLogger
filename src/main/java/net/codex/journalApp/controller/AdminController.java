package net.codex.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import net.codex.journalApp.cache.AppCache;
import net.codex.journalApp.entity.User;
import net.codex.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = " ADMIN APIs", description = "admin creation,fetch,clear-cache")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-users")
    public void createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();

    }
}
