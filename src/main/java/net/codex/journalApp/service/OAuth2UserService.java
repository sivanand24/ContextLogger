package net.codex.journalApp.service;

import net.codex.journalApp.entity.User;
import net.codex.journalApp.enums.AuthProvider;
import net.codex.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    public OAuth2User loadUser(
            OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oauthUser =
                super.loadUser(userRequest);

        String email =
                oauthUser.getAttribute("email");

        String name =
                oauthUser.getAttribute("name");

        String googleId =
                oauthUser.getAttribute("sub");

        User user = userRepository
                .findByEmail(email)
                .orElse(null);

        if (user == null) {

            user = new User();

            user.setEmail(email);
            user.setUserName(name);

            user.setProvider(
                    AuthProvider.GOOGLE
            );

            user.setProviderId(googleId);

            user.setRoles(
                    List.of("USER")
            );

            userRepository.save(user);
        }

        return oauthUser;
    }
}
