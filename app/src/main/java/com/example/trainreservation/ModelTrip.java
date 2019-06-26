package com.example.trainreservation;

public class ModelTrip {
    private String ID, departure , arrival , triptype ,  departuredate , arrivaldate ,  number_passanger , traintype ;

    public  ModelTrip () {}

    public  ModelTrip (String ID ,String departure , String arrival ,String triptype , String departuredate ,String arrivaldate , String number_passanger ,String traintype) {
        this.ID = ID ;
        this.departure = departure ;
        this.arrival = arrival ;
        this.triptype = triptype ;
        this.departuredate = departuredate ;
        this.arrivaldate = arrivaldate ;
        this.number_passanger = number_passanger ;
        this.traintype = traintype ;
    }


    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getTriptype() {
        return triptype;
    }

    public void setTriptype(String triptype) {
        this.triptype = triptype;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public String getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(String arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public String getNumber_passanger() {
        return number_passanger;
    }

    public void setNumber_passanger(String number_passanger) {
        this.number_passanger = number_passanger;
    }

    public String getTraintype() {
        return traintype;
    }

    public void setTraintype(String traintype) {
        this.traintype = traintype;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
