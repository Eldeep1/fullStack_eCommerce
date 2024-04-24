package com.e_commerce.e_commerce.security_configurations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.e_commerce.e_commerce.models.UserDetailsImp;
import com.e_commerce.e_commerce.security_configurations.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsImp userDetailsImp;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(UserDetailsImp userDetailsImp, JwtAuthenticationFilter jwtAuthenticationFilter,
            CustomAccessDeniedHandler accessDeniedHandler) {
        this.userDetailsImp = userDetailsImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    req->req
                    .requestMatchers("market/Orders/updateStatus")
                    .hasAuthority("ADMIN").
                    requestMatchers("/market/login/**", "/market/signup/**","/market/products/**","/market/Orders/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                ).userDetailsService(userDetailsImp)
                .exceptionHandling(e -> e.accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .requiresChannel(meow->meow.anyRequest().requiresSecure())
                .build();  
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    

}
