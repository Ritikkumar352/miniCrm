package com.xeno.crm.miniCrm.config;

import com.xeno.crm.miniCrm.repository.AdminRepo;
import com.xeno.crm.miniCrm.service.Impl.OAuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AdminRepo adminRepo;
    public SecurityConfig(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(csrf -> csrf.disable()) // for postman only
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()   // change to permitAll() for testing in postman and back to authenticated for security
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(new OAuthServiceImpl(adminRepo))
                        )
                        .defaultSuccessUrl("/", true)
                );
        return http.build();
    }


}


// .logout(logout -> logout.logoutSuccessUrl("/")
//                );