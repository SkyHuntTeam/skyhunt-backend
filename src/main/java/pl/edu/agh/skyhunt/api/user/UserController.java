package pl.edu.agh.skyhunt.api.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.skyhunt.api.user.dto.UserDto;
import pl.edu.agh.skyhunt.api.user.dto.UserRegistration;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper mapper;

    private final UserService userService;

    @PostMapping
    public UserDto register(@RequestBody UserRegistration userRegistration) {

        return mapper.map(userService.register(mapper.map(userRegistration, User.class),
                        userRegistration.getPassword()), UserDto.class);
    }
}
