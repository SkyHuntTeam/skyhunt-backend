package pl.edu.agh.skyhunt.api.connection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.skyhunt.api.connection.dto.*;
import pl.edu.agh.skyhunt.api.user.UserService;
import pl.edu.agh.skyhunt.exception.security.NoLoggedUserException;

import java.net.ConnectException;
import java.util.Optional;

@RestController
@RequestMapping("/api/connections")
@Slf4j
@RequiredArgsConstructor
public class ConnectionController {
    private final ModelMapper mapper;
    private final UserService userService;
    private final ConnectionService connectionService;


    @GetMapping("/MyConnectionsList")
    public ConnectionMineList myConnectionList() throws NoLoggedUserException {
            return connectionService.myConnectionList(userService.getLoggedUser());
    }

    @GetMapping("/MyConnectionsList/MyConnection")
    public ConnectionMine myConnection(@RequestBody Long id) throws NoLoggedUserException {
        return connectionService.getMyConnection(id, userService.getLoggedUser());
    }

    @GetMapping("/ConnectionSearchedList")
    public ConnectionSearchedList connectionSearchedList(@RequestBody ConnectionSearchingParameters parameters)  {
        return connectionService.searchConnections(parameters);
    }

    @GetMapping("/ConnectionSearchedList/ConnectionSearched")
    public ConnectionSearched connectionSearched(@RequestBody Long id) throws ConnectException {
        return connectionService.getSearchedConnection(id);
    }

    @PostMapping("/ConnectionSearchedList/ConnectionSearched")
    public boolean book(@RequestBody ConnectionSearched connection) throws NoLoggedUserException {
        return connectionService.bookConnection(connection, userService.getLoggedUser());
    }

    @DeleteMapping("/MyConnectionsList/MyConnection")
    public boolean cancel(@RequestBody ConnectionMine connection) throws NoLoggedUserException {
        return connectionService.cancelConnection(connection, userService.getLoggedUser());
    }
    
}
