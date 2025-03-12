package com.ha1_microservice.group9.dto.response;

import com.ha1_microservice.group9.entity.Measurement;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Representing the response structure for a measurement, which is returned to the client.
 * Contains information about timestamp, temperature and humidity.
 */
@Getter
@Setter
public class MeasurementResponse {

    private int id;
    private Timestamp timestamp;
    private Double temperature;
    private Double humidity;
    private Integer sensorId;

    public MeasurementResponse(Measurement measurement) {
        this.id = measurement.getId();
        this.timestamp = measurement.getTimestamp();
        this.temperature = measurement.getTemperature();
        this.humidity = measurement.getHumidity();
        this.sensorId = measurement.getSensor() != null ? measurement.getSensor().getId() : null;
    }

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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }
}
