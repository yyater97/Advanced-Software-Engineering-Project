package com.example.banana.webservicedbdemo;

public class TypeExpense {
    private String Name;
    private String Description;



    public TypeExpense(String Name, String Description){
        this.Name = Name;
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
