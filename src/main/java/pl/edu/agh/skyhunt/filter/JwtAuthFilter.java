package pl.edu.agh.skyhunt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.agh.skyhunt.exception.authentication.InvalidAuthHeaderException;
import pl.edu.agh.skyhunt.utils.JwtUtils;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            var token = jwtUtils.getToken(request.getHeader(AUTHORIZATION));

            var userEmail = "abc@gmail.com";
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            }
        }
        catch(InvalidAuthHeaderException exception) {
            filterChain.doFilter(request, response);
        }
    }
}
