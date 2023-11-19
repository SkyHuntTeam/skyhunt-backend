package pl.edu.agh.skyhunt.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    JwtService jwtService;

    UserDetails userDetails;

    @BeforeEach
    void initializeData() {

        jwtService = new JwtService();

        userDetails = new User("some@abc.com", "dsa#$dfsd@d", new LinkedList<>());
    }

    @Test
    void jwtTokenGeneration() {

        var claims = new HashMap<String, Object> ();
        claims.put("phone", 123456789);

        var token = jwtService.createToken(userDetails, claims);

        assertNotNull(token);

        assertEquals(jwtService.extractUsername(token), userDetails.getUsername());

        assertFalse(jwtService.isTokenExpired(token));
    }
}