package com.test.aliveCore.models;

public class Age {
    private int mins;
    private int hour;
    private int days;
    private int months;
    private int years;

    private Age() {
        //Prevent default constructor
    }

    public Age(int mins, int hour, int days, int months, int years) {
        this.mins = mins;
        this.hour = hour;
        this.days = days;
        this.months = months;
        this.years = years;
    }

    public Age(int days, int months, int years) {
        this.days = days;
        this.months = months;
        this.years = years;
    }

    public int getDays() {
        return this.days;
    }

    public int getMonths() {
        return this.months;
    }

    public int getYears() {
        return this.years;
    }

    public int getMins() {
        return mins;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public String toString() {
        return years + " Years, " + months + " Months, " + days + " Days";
    }
}
