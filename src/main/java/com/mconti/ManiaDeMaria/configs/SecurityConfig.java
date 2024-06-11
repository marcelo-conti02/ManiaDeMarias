package com.mconti.ManiaDeMaria.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mconti.ManiaDeMaria.security.JWTAuthenticationFilter;
import com.mconti.ManiaDeMaria.security.JWTAuthorizationFilter;
import com.mconti.ManiaDeMaria.security.JWTUtil;
import com.mconti.ManiaDeMaria.services.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        @Autowired
        private UserDetailsServiceImp userDetailsService;

        @Autowired
        private JWTUtil jwtUtil;

        private AuthenticationManager authenticationManager;

        private static final String[] PUBLIC_MATCHERS = {
                        "/"
        };
        private static final String[] PUBLIC_MATCHERS_POST = {
                        "/user",
                        "/login"
        };
        private static final String[] PUBLIC_MATCHERS_GET = {
                        "/product"
        };

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                                .passwordEncoder(bCryptPasswordEncoder());
                this.authenticationManager = authenticationManagerBuilder.build();

                http
                                .cors(withDefaults()).csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests((authz) -> authz
                                                .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                                                .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                                                .requestMatchers(PUBLIC_MATCHERS).permitAll()
                                                .anyRequest().authenticated())
                                .authenticationManager(this.authenticationManager)
                                .addFilter(new JWTAuthenticationFilter(this.authenticationManager, this.jwtUtil))
                                .addFilter(new JWTAuthorizationFilter(this.authenticationManager, this.jwtUtil,
                                                this.userDetailsService))
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                return http.build();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
                configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
                final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
