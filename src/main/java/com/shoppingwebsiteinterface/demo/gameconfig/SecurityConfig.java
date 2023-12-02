package com.shoppingwebsiteinterface.demo.gameconfig;

import com.shoppingwebsiteinterface.demo.service.implementation.CustomUserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsServiceImplementation userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/gamey/createuser").permitAll()
                        .requestMatchers("/gamey/home", "/gamey/owned", "/gamey/cart").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/gamey/login")
                        .defaultSuccessUrl("/gamey/explore")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/gamey/logout")  // URL to trigger logout
                        .logoutSuccessUrl("/gamey/login") // Redirect back to the login page
                        .invalidateHttpSession(true) // Invalidate the session
                        .deleteCookies("JSESSIONID") // Optionally delete session cookies
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .build();
    }

}
