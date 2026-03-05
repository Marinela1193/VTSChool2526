package org.example.vtschool_mps_2526.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth

                        // Recursos públicos
                        .requestMatchers("/index", "/css/", "/js/", "/images/", "/favicon.ico").permitAll()

                        // Rutas ADMIN
                        .requestMatchers("/admin/").hasRole("ADMIN")

                        // Rutas STUDENT
                        .requestMatchers("/student/**").hasRole("STUDENT")

                        // Todo lo demás requiere login
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/index")
                        .successHandler((request, response, authentication) -> {

                            var authorities = authentication.getAuthorities();

                            boolean isAdmin = authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

//                            if (isAdmin) {
//                                response.sendRedirect("/admin/home");
//                            } else {
//                                response.sendRedirect("/student/home");
//                            }

                            if (isAdmin) {
                                response.sendRedirect("/admin/home");
                            } else {
                                response.sendRedirect("/student/home");
                            }
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}

