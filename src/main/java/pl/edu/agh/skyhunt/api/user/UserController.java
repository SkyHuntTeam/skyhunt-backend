package pl.edu.agh.skyhunt.api.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.skyhunt.api.user.dto.UserAuthentication;
import pl.edu.agh.skyhunt.api.user.dto.UserDto;
import pl.edu.agh.skyhunt.api.user.dto.UserRegistration;
import pl.edu.agh.skyhunt.api.user.dto.UserToken;
import pl.edu.agh.skyhunt.exception.security.InvalidRegisterFormException;
import pl.edu.agh.skyhunt.exception.security.NoLoggedUserException;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper mapper;

    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserRegistration userRegistration) throws InvalidRegisterFormException {

        return mapper.map(userService.register(mapper.map(userRegistration, User.class),
                        userRegistration.getPassword(), userRegistration.acceptedConsents()), UserDto.class);
    }

    @PostMapping("/login")
    public UserToken login(@RequestBody UserAuthentication userAuthentication) {

        return new UserToken(userService.login(userAuthentication.getEmail(),
                userAuthentication.getPassword()));
    }

    @GetMapping
    public UserDto loggedUser() throws NoLoggedUserException {

        return mapper.map(userService.getLoggedUser(), UserDto.class);
    }
}
