package com.pambani.taskverse.orgahive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity  // Enables Spring Security for the application
public class SecurityConfig implements WebMvcConfigurer {

    /**
     * Configure CORS (Cross-Origin Resource Sharing) to allow the React frontend
     * to communicate with the Spring Boot backend.
     *
     * @param registry CorsRegistry for configuring cross-origin requests.
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // Allow cross-origin requests from specific origins (React app running on localhost:3000)
//        registry.addMapping("/api/**")  // This applies to all routes starting with /api/
//                .allowedOrigins("http://localhost:3000") // React frontend URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Allow these HTTP methods
//                .allowedHeaders("*") // Allow all headers from the frontend
//                .allowCredentials(true); // Allow credentials (e.g., cookies or sessions)
//    }

    /**
     * Configure HTTP security for the Spring Boot application. This setup disables
     * CSRF protection (for stateless API communication), disables form-based login,
     * and configures public access to specific authentication routes (signup, login).
     *
     * @param http HttpSecurity for configuring security rules.
     * @return SecurityFilterChain to apply the security configuration.
     * @throws Exception if there is an error in the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection (needed for stateless applications like REST APIs)
//                .csrf(AbstractHttpConfigurer::disable)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/auth/**"))
                // Configure public access to authentication endpoints (signup, login)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**", "/api/auth/signup", "/api/auth/login", "/api/auth/login/success")
                        .permitAll() // These routes are open to everyone
                        .anyRequest().authenticated() // All other routes require authentication
                );
//                .httpBasic(AbstractHttpConfigurer::disable) // Disable basic HTTP authentication (not needed here)
//                .formLogin(AbstractHttpConfigurer::disable); // Disable form-based login (use JWT or other auth mechanisms)

        return http.build(); // Return the configured SecurityFilterChain
    }

    /**
     * Password encoder to securely hash passwords. BCryptPasswordEncoder is used here.
     * BCrypt is a strong hashing algorithm that is widely used for password storage.
     *
     * @return PasswordEncoder instance (BCryptPasswordEncoder)
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();  // BCryptPasswordEncoder is used to hash passwords
//    }
}
