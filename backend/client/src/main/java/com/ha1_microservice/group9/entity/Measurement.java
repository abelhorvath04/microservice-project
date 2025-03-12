package com.ha1_microservice.group9.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Representing a measurement, which is measured from a sensor.
 * Contains information about timestamp, temeperature and humidity.
 * Saved as entity for the database and can be managed by JPA.
 */

@Getter
@Setter
@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_seq")
    @SequenceGenerator(name = "measurement_seq", sequenceName = "measurement_seq", allocationSize = 1)
    private int id;

    private Timestamp timestamp;
    private double temperature;
    private double humidity;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
