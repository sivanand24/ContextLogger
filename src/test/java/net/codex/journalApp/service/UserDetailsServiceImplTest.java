package net.codex.journalApp.service;

import net.codex.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public  void loadUserByUsernameTest(){
       // when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("weteetyrtryj").roles(new ArrayList<>()).build())
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
