package pl.edu.agh.skyhunt.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.agh.skyhunt.exception.authentication.InvalidAuthHeaderException;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtUtils {

    private final String signingKey = "secret";

    public String getToken(String authHeader) throws InvalidAuthHeaderException {

        if(authHeader == null || !authHeader.startsWith("Bearer")) {
           throw new InvalidAuthHeaderException();
        }

        return authHeader.substring(7);
    }

    public String createToken(UserDetails userDetails, Map<String, Object> claims) {

        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("Authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }
}
