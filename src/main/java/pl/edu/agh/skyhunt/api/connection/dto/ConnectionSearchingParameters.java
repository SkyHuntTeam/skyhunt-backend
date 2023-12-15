package pl.edu.agh.skyhunt.api.connection.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ConnectionSearchingParameters
{
    private String StartAirport;
    private String FinishAirport;
    private Date StartAfter;
    private Date ArriveBefore;
    //private int Seats;
    //private int MaxFligts;

}
