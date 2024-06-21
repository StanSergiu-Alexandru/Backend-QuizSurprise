package com.Licenta.QuizSurprise.Config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.Licenta.QuizSurprise.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//              .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
//                      .permitAll().anyRequest().authenticated())
//                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//                .authenticationProvider(authenticationProvider()).addFilterBefore(
//                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll()) // Allow all requests without authentication
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS));
        return http.build();
    }

}