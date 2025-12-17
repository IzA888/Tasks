package com.example.Tasks.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        return http
            .cors(cors -> cors.configurationSource(corsConfigurationSoucer()))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository())
                .csrfTokenRequestHandler((request, response, supplier) -> {
                    supplier.get();
                    request.getHeader("X-XSRF-TOKEN");
                })
                .ignoringRequestMatchers("/user/login")
                .ignoringRequestMatchers("/user/save")
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/user/save").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    } 

    private CsrfTokenRepository CookieCsrfTokenRepository() {
       CookieCsrfTokenRepository repo = CookieCsrfTokenRepository.withHttpOnlyFalse();
            repo.setCookieCustomizer(cookie -> cookie
                .httpOnly(false)
                .path("/")
                .secure(false)
                .sameSite("Lax")
            );
        return repo;
    }

    @Bean
    public AuthenticationManager authenticatioManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }    

    private CorsConfigurationSource corsConfigurationSoucer() {
       CorsConfiguration source = new CorsConfiguration();
        source.setAllowedOrigins(List.of("http://localhost:4200"));
        source.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        source.setAllowedHeaders(List.of("*"));
        source.setAllowCredentials(true);
        source.setExposedHeaders(List.of("XSRF-TOKEN", "Authorization"));
       
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", source);
        return corsSource;
    }
}
