package pl.edu.agh.skyhunt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config ->
                config.anyRequest().authenticated()
        ).exceptionHandling(config ->
                config.accessDeniedHandler((request, response, exception) ->
                            response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage())
                )
        ).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
