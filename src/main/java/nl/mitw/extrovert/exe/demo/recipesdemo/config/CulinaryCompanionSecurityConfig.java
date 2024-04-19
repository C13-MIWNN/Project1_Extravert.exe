package nl.mitw.extrovert.exe.demo.recipesdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Jeroen van der Weide
 * <p>
 * Configures security for our recipeOrganizer application
 **/

@Configuration
@EnableWebSecurity
public class CulinaryCompanionSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/recipe").permitAll()
                        .requestMatchers("/webjars/**", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form.permitAll())
                //custom login form possible here
                .logout((logout) -> logout.logoutSuccessUrl("/").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
