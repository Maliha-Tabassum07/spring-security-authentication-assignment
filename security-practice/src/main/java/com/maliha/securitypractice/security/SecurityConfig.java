package com.maliha.securitypractice.security;

import com.maliha.securitypractice.constants.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)throws Exception{
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth-> {
                    auth
                            .requestMatchers(HttpMethod.GET,"/security/welcome").permitAll()
                            .requestMatchers(HttpMethod.GET,"/security/user").authenticated()
                            .requestMatchers(HttpMethod.GET,"security/role").hasAnyRole("user","admin")
                            .requestMatchers(HttpMethod.GET,"/security/role/admin").hasRole("admin")
                            .requestMatchers(HttpMethod.GET,"/security/role/user/**").hasRole("user")
                            .requestMatchers(HttpMethod.GET,"/security/authority/read").hasRole("READ_ONLY")
                            .requestMatchers(HttpMethod.GET,"/security/authority/edit/**").hasAnyAuthority("EDIT_USER", "EDIT_ADMIN")
                            .requestMatchers(HttpMethod.POST, AppConstants.SIGN_IN,AppConstants.SIGN_UP).permitAll()
                            .anyRequest().authenticated();
                })
                .addFilter(new CustomAuthenticationFilter(authenticationManager));


        return httpSecurity.build();

    }

}
