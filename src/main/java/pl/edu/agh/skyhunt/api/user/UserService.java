package pl.edu.agh.skyhunt.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user, String password) {

        user.setHashedPassword(password);

        return userRepository.save(user);
    }
}
