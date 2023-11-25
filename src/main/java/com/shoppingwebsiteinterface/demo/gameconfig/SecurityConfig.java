//package com.shoppingwebsiteinterface.demo.gameconfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin()
//                .loginPage("/gamey/home")  // Points to the home page where login popup is triggered
//                .defaultSuccessUrl("/loggedin")
//                .permitAll().and()
//                .logout(logout -> logout.permitAll())
//                .csrf(csrf -> csrf.disable())
//                .build();
//    }
//}
