package pl.edu.agh.skyhunt.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.agh.skyhunt.exception.security.NoLoggedUserException;
import pl.edu.agh.skyhunt.exception.security.InvalidRegisterFormException;
import pl.edu.agh.skyhunt.security.JwtService;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public User register(User user, String password, boolean acceptedConsents) throws InvalidRegisterFormException {

        if(!acceptedConsents) {
            throw new InvalidRegisterFormException("User must accept consents");
        }

        if(password.contains(" ") || password.length() < 4) {
            throw new InvalidRegisterFormException(
                    "Password length cant be lower than 4 and white characters are not allowed");
        }

        if(!user.getEmail().contains("@")) {
            throw new InvalidRegisterFormException("Invalid email format");
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new InvalidRegisterFormException("User with provided email already exists");
        }

        user.setHashedPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        var user = userRepository.findUserByEmail(email)
                .orElseThrow();

        return jwtService.createToken(user);
    }

    public User getLoggedUser() throws NoLoggedUserException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            throw new NoLoggedUserException();
        }

        return userRepository.findUserByEmail(authentication.getName())
                .orElseThrow(NoLoggedUserException::new);
    }
}
