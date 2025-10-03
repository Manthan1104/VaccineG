package com.vaccine.management.security.oauth2;

import com.vaccine.management.model.AuthProvider;
import com.vaccine.management.model.Role;
import com.vaccine.management.model.User;
import com.vaccine.management.repository.UserRepository;
import com.vaccine.management.security.JwtUtil;
import com.vaccine.management.security.MyUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthenticationSuccessHandler.class);


    public OAuth2AuthenticationSuccessHandler(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        log.info("OAuth2 success for user email: {}", email);

        Optional<User> userOptional = userRepository.findByUsername(email);
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
            log.info("Existing user found: {}", email);
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                log.warn("Existing user {} is missing roles. Assigning default PARENT role.", email);
                user.setRoles(Collections.singleton(Role.ROLE_PARENT));
                user = userRepository.save(user);
            }
        } else {
            log.info("User not found, creating a new user for email: {}", email);
            user = new User();
            user.setUsername(email);
            user.setEmail(email);
            user.setName(name);
            user.setAuthProvider(AuthProvider.GOOGLE);
            user.setRoles(Collections.singleton(Role.ROLE_PARENT));
            user = userRepository.save(user);
        }

        MyUserDetailsService.CustomUserDetails userDetails = new MyUserDetailsService.CustomUserDetails(user);

        String token = jwtUtil.generateToken(userDetails);
        
        // **CRITICAL FIX**: Build the redirect URL dynamically from the original request.
        // This will correctly use https://vccinegaurd.onrender.com when deployed.
        String targetUrl = UriComponentsBuilder.fromUriString(getFrontendBaseUrl(request))
                .queryParam("token", token)
                .build().toUriString();
        
        log.info("Redirecting user {} to target URL: {}", email, targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String getFrontendBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(serverName);

        // Only include the port if it's not a standard port (80 for http, 443 for https)
        if (!("http".equals(scheme) && serverPort == 80) && !("https".equals(scheme) && serverPort == 443)) {
            builder.port(serverPort);
        }

        return builder.path("/").build().toUriString();
    }
}
