package net.codex.journalApp.service;

import net.codex.journalApp.entity.User;
import net.codex.journalApp.enums.AuthProvider;
import net.codex.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(
            OidcUserRequest userRequest)
            throws OAuth2AuthenticationException {

        OidcUser oidcUser =
                super.loadUser(userRequest);

        String email =
                oidcUser.getEmail();

        String name =
                oidcUser.getFullName();

        String googleId =
                oidcUser.getSubject();

        System.out.println(
                "Google login: " + email
        );

        User user =
                userRepository
                        .findByEmail(email)
                        .orElse(null);

        if (user == null) {

            user = new User();

            user.setEmail(email);
            user.setUserName(name);

            user.setProvider(
                    AuthProvider.GOOGLE
            );

            user.setProviderId(
                    googleId
            );

            user.setRoles(
                    List.of("USER")
            );

            userRepository.save(user);

            System.out.println(
                    "Google user saved: " + email
            );
        }

        return oidcUser;
    }

}
