package pl.edu.agh.skyhunt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.skyhunt.utils.JwtUtils;

@Configuration
public class UtilsConfig {

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}
