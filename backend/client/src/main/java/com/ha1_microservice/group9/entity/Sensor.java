package com.ha1_microservice.group9.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Representing a sensor-device, which measures temperature and humidity.
 * Each sensor has an unique id, name, location, activity and type.
 */

@Getter
@Setter
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_seq")
    @SequenceGenerator(name = "sensor_seq", sequenceName = "sensor_seq", allocationSize = 1)
    private int id;

    private String name;
    private String location;
    private boolean active;
    private String type;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurementList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Measurement> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
    }
}