package pl.edu.agh.skyhunt.api.connection.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ConnectionSearched
{

    private Long id;
    private String StartAirport;
    private String FinishAirport;
    private Date StartTime;
    private Date FinishTime;
    //private ArrayList<Object> Fligts;
    //private int Seats;
    //private double PaidPrice;

    private int NumberOfFlights;
    private double Price;
    private int FreeSeats;



}
