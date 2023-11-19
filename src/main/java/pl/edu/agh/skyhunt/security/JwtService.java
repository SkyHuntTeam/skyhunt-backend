package pl.edu.agh.skyhunt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.edu.agh.skyhunt.exception.security.InvalidAuthHeaderException;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private final String signingKey = "secret";

    private final String issuer = "SkyHunt";

    public String getToken(String authHeader) throws InvalidAuthHeaderException {

        if(authHeader == null || !authHeader.startsWith("Bearer")) {
           throw new InvalidAuthHeaderException();
        }

        return authHeader.substring(7);
    }

    public String createToken(UserDetails userDetails, Map<String, Object> claims) {

        return Jwts.builder().setClaims(claims)
                .setIssuer(issuer)
                .setSubject(userDetails.getUsername())
                .claim("Authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public String createToken(UserDetails userDetails) {

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(userDetails.getUsername())
                .claim("Authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
    }
}
