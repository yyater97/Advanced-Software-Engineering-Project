package com.learnadroid.myfirstapp;

/**
 * Created by QUANPN on 5/19/2018.
 */

public class Income {
    private String countryName;

    // Image name (Without extension)
    private String flagName;
    private int population;
    private String Date;

    public Income(String countryName, String flagName, int population,String date ) {
        this.countryName= countryName;
        this.flagName= flagName;
        this.population= population;
        this.Date=date;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString()  {
        return this.countryName+" (Population: "+ this.population+")";
    }
}