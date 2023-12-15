package pl.edu.agh.skyhunt.api.connection;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.skyhunt.api.user.User;

import java.util.Date;


@Entity
@Data
@Getter
@Setter
@Table(name = "portal_connection")

public class Connection {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String StartAirport;

    @Column(nullable = false)
    private String FinishAirport;

    @Column(unique = true, nullable = false)
    private Date StartTime;

    @Column(nullable = false)
    private Date FinishTime;

    @Column(nullable = true)
    private boolean expired;

    @Column(nullable = true)
    private double Price;
    //----------------
    @Column(nullable = false)
    private double PaidPrice;

    @Column(nullable = true)
    private int Seats;
    //-----------------
    @Column(nullable = true)
    private int FreeSeats;



//    @ManyToMany(mappedBy = "connection")
//    private List<Flight> flights;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}


