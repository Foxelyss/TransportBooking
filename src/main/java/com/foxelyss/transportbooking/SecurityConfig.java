package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.repos.UserRepo;
import com.foxelyss.transportbooking.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
/// /@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private UserRepo userService;
//
//    //    @Override

/// /    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/// /        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
/// /    }
/// /
/// /    @Override
/// /    protected void configure(HttpSecurity http) throws Exception {
/// /        http.csrf().disable()
/// /                .authorizeRequests()
/// /                .antMatchers("/api/auth/login").permitAll()
/// /                .anyRequest().authenticated();
/// /    }
/// /    @Bean
/// /    public WebSecurityCustomizer webSecurityCustomizer() {
/// /        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
/// /    }
/// /
/// /    @Bean
/// /    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
/// /        http
/// /                .authorizeHttpRequests((authz) ->
/// /                                authz.anyRequest().authenticated()
/// ///                        authz.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
/// /                )
/// /                .httpBasic(withDefaults());
/// /        return http.build();
/// /    }
//
//}

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/api/auth/login").permitAll()
//                .anyRequest().authenticated();
//        return http.build();
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

