package com.serviceexchange.www.serviceexchangeandroid.earning;

public class Pojo {

    String serviceName,points,date;
    int rate;

    public Pojo(String serviceName, String points, int rate) {
        this.serviceName = serviceName;
        this.points = points;
        this.rate = rate;
    }

    public Pojo(String serviceName, String points, String date) {
        this.serviceName = serviceName;
        this.points = points;
        this.date = date;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
