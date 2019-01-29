package com.example.ngailapdi.cs4261;

public class Team {

    private String name;
    private String description;
    private String id;

    public Team(){

    }

    public Team(String name, String description, String id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
