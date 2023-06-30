import java.sql.*;

public class Flight {
    private int flight_id;
    private String destination;
    private String origin;
    private Date departure;
    private Date arrival;
    private boolean stopover ;
    private int stoptime;
    private int seats_number;
    private String Fstatus;
    private String airline;
    private String aircraft;
    // Constructor
    public Flight(int flight_id, String destination, String origin, Date departure, Date arrival,
                  boolean stopover, int stoptime, int seats_number, String Fstatus, String airline, String aircraft) {
        this.flight_id = flight_id;
        this.destination = destination;
        this.origin = origin;
        this.departure = departure;
        this.arrival = arrival;
        this.stopover = stopover;
        this.stoptime = stoptime;
        this.seats_number = seats_number;
        this.Fstatus = Fstatus;
        this.airline = airline;
        this.aircraft = aircraft;
    }

    // Getters and Setters
    public int getFlightId() {
        return flight_id;
    }

    public void setFlightId(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public boolean hasStopover() {
        return stopover;
    }

    public void setStopover(boolean stopover) {
        this.stopover = stopover;
    }

    public int getStopTime() {
        return stoptime;
    }

    public void setStopTime(int stoptime) {
        this.stoptime = stoptime;
    }

    public int getSeatsNumber() {
        return seats_number;
    }

    public void setSeatsNumber(int seats_number) {
        this.seats_number = seats_number;
    }

    public String getStatus() {
        return Fstatus;
    }

    public void setStatus(String Fstatus) {
        this.Fstatus = Fstatus;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }
}


