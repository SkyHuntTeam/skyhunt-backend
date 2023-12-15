package pl.edu.agh.skyhunt.api.connection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.skyhunt.api.connection.dto.*;
import pl.edu.agh.skyhunt.api.user.User;
import pl.edu.agh.skyhunt.exception.security.NoLoggedUserException;

import java.net.ConnectException;
import java.nio.channels.NoConnectionPendingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository ConnectionRepository;

    public ConnectionMineList myConnectionList(User user) throws NoLoggedUserException {
        if (user == null) {
            throw new NoLoggedUserException();
        }
        return (ConnectionMineList) ConnectionRepository.findByUserIdAndExpiredIsFalse(user.getId());
    }

    public ConnectionMine getMyConnection(Long id, User loggedUser) throws NoLoggedUserException {
        if (loggedUser == null) {
            throw new NoLoggedUserException();
        }
        Optional<Connection> connection = ConnectionRepository.findConnectionByIdAndUserId(id, loggedUser.getId());
        ConnectionMine connectionMine = new ConnectionMine();
        if (connection.isPresent()){
            connectionMine.setId(connection.get().getId());
            connectionMine.setStartAirport(connection.get().getStartAirport());
            connectionMine.setFinishAirport(connection.get().getFinishAirport());
            connectionMine.setStartTime(connection.get().getStartTime());
            connectionMine.setFinishTime(connection.get().getFinishTime());
//            connectionMine.setFligts(connection.get().getFlights());
            connectionMine.setSeats(connection.get().getSeats());
            connectionMine.setPaidPrice(connection.get().getPaidPrice());
        } else {
            throw new NoLoggedUserException();
        }


        return connectionMine;
    }

    public ConnectionSearchedList searchConnections(ConnectionSearchingParameters parameters) {
        return (ConnectionSearchedList) ConnectionRepository.findAllByStartAirportAndFinishAirportAndStartTimeAfterAndFinishTimeBefore(parameters.getStartAirport(), parameters.getFinishAirport(), parameters.getStartAfter(), parameters.getArriveBefore());
    }

    public ConnectionSearched getSearchedConnection(Long id) throws ConnectException {
        Optional<Connection> connection = ConnectionRepository.findConnectionById(id);
        ConnectionSearched connectionSearched = new ConnectionSearched();

        if (connection.isPresent()) {
            connectionSearched.setId(connection.get().getId());
            connectionSearched.setStartAirport(connection.get().getStartAirport());
            connectionSearched.setFinishAirport(connection.get().getFinishAirport());
            connectionSearched.setStartTime(connection.get().getStartTime());
            connectionSearched.setFinishTime(connection.get().getFinishTime());
            connectionSearched.setNumberOfFlights(connectionSearched.getNumberOfFlights());
            connectionSearched.setPrice(connection.get().getPrice());
            connectionSearched.setFreeSeats(connection.get().getFreeSeats());
        } else throw new ConnectException();


        return connectionSearched;
    }

    public boolean bookConnection(ConnectionSearched connectionSearched, User loggedUser) {
        if (loggedUser == null){
            return false;
        }

        Connection connection = new Connection();

        connection.setExpired(false);
        connection.setId(connectionSearched.getId());
        connection.setFinishAirport(connection.getFinishAirport());
        connection.setFinishTime(connectionSearched.getFinishTime());
        connection.setPrice(connectionSearched.getPrice());
        connection.setSeats(connection.getSeats());
        connection.setStartAirport(connectionSearched.getStartAirport());
        connection.setStartTime(connectionSearched.getStartTime());
        connection.setUser(loggedUser);
        connection.setPaidPrice(-1);

        ConnectionRepository.save(connection);
        return true;
    }

    public boolean cancelConnection(ConnectionMine connectionMine, User loggedUser) {
        if (loggedUser == null || connectionMine == null){
            return false;
        }

        Connection connection = new Connection();

        connection.setExpired(false);
        connection.setId(connectionMine.getId());
        connection.setFinishAirport(connectionMine.getFinishAirport());
        connection.setFinishTime(connectionMine.getFinishTime());
        connection.setPaidPrice(connectionMine.getPaidPrice());
        connection.setSeats(connectionMine.getSeats());
        connection.setStartAirport(connectionMine.getStartAirport());
        connection.setStartTime(connectionMine.getStartTime());
        connection.setUser(loggedUser);
        connection.setPaidPrice(-1);

        ConnectionRepository.delete(connection);
        return true;
    }
}

