
public class Reservation {
    private int reservation_id;
    private int passenger_id;
    private int flight_id;
    private int seat_id;
    private String Flightclass;
    private float price;


    // Constructor
    public Reservation(int reservation_id, int passenger_id, int flight_id, int seat_id,
                       String Flightclass, float price) {
        this.reservation_id = reservation_id;
        this.passenger_id = passenger_id;
        this.flight_id = flight_id;
        this.seat_id = seat_id;
        this.Flightclass = Flightclass;
        this.price = price;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservation_id;
    }

    public void setReservationId(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getPassengerId() {
        return passenger_id;
    }

    public void setPassengerId(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public int getFlightId() {
        return flight_id;
    }

    public void setFlightId(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getSeatId() {
        return seat_id;
    }

    public void setSeatId(int seat_id) {
        this.seat_id = seat_id;
    }

    public String getFlightclass() {
        return Flightclass;
    }

    public void setFlightclass(String Flightclass) {
        this.Flightclass = Flightclass;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
