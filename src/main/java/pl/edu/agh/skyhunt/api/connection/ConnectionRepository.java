package pl.edu.agh.skyhunt.api.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.agh.skyhunt.api.connection.dto.ConnectionSearched;
import pl.edu.agh.skyhunt.api.connection.dto.ConnectionSearchedList;
import pl.edu.agh.skyhunt.api.connection.dto.ConnectionSearchingParameters;
import pl.edu.agh.skyhunt.api.user.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConnectionRepository  extends JpaRepository<Connection, Long> {
    List<Connection> findByUserIdAndExpiredIsFalse(Long userId);

    List<Connection> findByUserIdAndExpiredIsTrue(Long userId);


    Optional<Connection> findConnectionById(Long Id);

    Optional<Connection> findConnectionByIdAndUserId(Long id, Long user_id);


    ConnectionSearchedList findAllByStartAirportAndFinishAirportAndStartTimeAfterAndFinishTimeBefore(String startAirport, String finishAirport, Date startAfter, Date arriveBefore);


    void save();
}