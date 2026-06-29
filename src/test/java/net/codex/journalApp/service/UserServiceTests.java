package net.codex.journalApp.service;

import net.codex.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.codex.journalApp.entity.User;
import net.codex.journalApp.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTests {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;


        @Disabled
        @ParameterizedTest
        @ArgumentsSource(UserArgumentsProvider.class)
        public void testSaveNewUser(User user) {
            assertTrue(userService.saveNewUser(user));
        }

        @Disabled
        @ParameterizedTest
        @CsvSource({
                "1,1,2",
                "2,10,12",
                "3,3,9"
        })
        public void test(int a, int b, int expected){
            assertEquals(expected, a + b);
        }
    }

