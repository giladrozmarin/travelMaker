package com.example.travelmaker.travelmaker.Models;

public class Trip {

    private String country;
    private String city;
    private String start_date;
    private String guide_name;
    private String end_date;
    private String travelers;
    private User guide;

    public Trip(String country, String city, String start_date,String end_date,String guide_name,String travelers) {
        this.country = country;
        this.city = city;
        this.start_date = start_date;
        this.end_date = end_date;
        this.guide_name = guide_name;
        this.travelers=travelers;

    }
   public Trip (){}

    public String getTravelers() {
        return travelers;
    }

    public void setTravelers(String travelers) {
        this.travelers = travelers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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



    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
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
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", guide_name='" + guide_name + '\'' +
                ", travelers='" + travelers + '\'' +
                '}';
    }
}
