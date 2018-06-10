package com.learnadroid.myfirstapp;

/**
 * Created by QUANPN on 5/23/2018.
 */

public class Danhmucthumua {
    private String countryName;
    // Image name (Without extension)
    private String flagName;
    private String population;
    private String Cohieu;



    public Danhmucthumua(String countryName, String flagName, String population, String cohieu) {
        this.countryName= countryName;
        this.flagName= flagName;
        this.population= population;
        this.Cohieu= cohieu;
    }
    public String getCohieu() {
        return Cohieu;
    }

    public void setCohieu(String cohieu) {
        Cohieu = cohieu;
    }
    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
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

    @Override
    public String toString()  {
        return this.countryName+" (Population: "+ this.population+")";
    }
}
