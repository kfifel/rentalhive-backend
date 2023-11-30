package com.rentalhive.security;

import com.rentalhive.domain.Organization;
import com.rentalhive.domain.User;
import com.rentalhive.repository.OrganizationRepository;
import com.rentalhive.repository.UserRepository;
import com.rentalhive.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class AuthenticationApp {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordUtil passwordUtil;
    @Bean
    public User userConnected() {
        Optional<User> userOptional = userRepository.findById(1L);
        Optional<Organization> optionalOrganization = organizationRepository.findById(1L);

        Organization rentalHive = optionalOrganization.orElseGet(() ->
            organizationRepository.save(
                    Organization.builder()
                    .name("Rental Hive")
                    .build()
            )
        );

        return userOptional.orElseGet(() -> {
            User newUser = User.builder()
                    .email("khalid.fifel@gmail.com")
                    .firstName("khalid")
                    .lastName("fifel")
                    .password(passwordUtil.encodePassword("password"))
                    .organization(rentalHive)
                    .build();
            return userRepository.save(newUser);
        });
    }
}
