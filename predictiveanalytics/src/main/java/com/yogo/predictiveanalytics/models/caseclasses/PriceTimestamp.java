package com.yogo.predictiveanalytics.models.caseclasses;

public class PriceTimestamp {
    private int month;
    private int day_month;
    private int day_week;
    private int hour_day;
    private double price;

    public PriceTimestamp() {
    }

    public PriceTimestamp(int month, int day_month, int day_week, int hour_day, double price) {
        this.month = month;
        this.day_month = day_month;
        this.day_week = day_week;
        this.hour_day = hour_day;
        this.price = price;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay_month() {
        return day_month;
    }

    public void setDay_month(int day_month) {
        this.day_month = day_month;
    }

    public int getDay_week() {
        return day_week;
    }

    public void setDay_week(int day_week) {
        this.day_week = day_week;
    }

    public int getHour_day() {
        return hour_day;
    }

    public void setHour_day(int hour_day) {
        this.hour_day = hour_day;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
