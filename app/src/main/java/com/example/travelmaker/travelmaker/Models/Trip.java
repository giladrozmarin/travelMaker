package com.example.travelmaker.travelmaker.Models;

public class Trip {

    private String trip_name;
    private String location;
    private String start_date;
   // private String guide_name;
    private String end_date;
    //private String travelers;
    private User guide;

    public Trip(String trip_name, String location, String start_date,String end_date) {
        this.trip_name = trip_name;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
        //this.guide_name = guide_name;
     //   this.travelers=travelers;

    }
   public Trip (){}


    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }





    public User getGuide() {
        return guide;
    }

    public void setGuide(User guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "country='" + trip_name + '\'' +
                ", city='" + location + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
               // ", guide_name='" + guide_name + '\'' +
               // ", travelers='" + travelers + '\'' +
                '}';
    }
}
