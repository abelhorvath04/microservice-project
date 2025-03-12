package com.ha1_microservice.group9.dto.createRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Representing data which is necessary for creating a new measurement in the API.
 * Contains information about timestamp, temperature and humidity.
 */
@Getter
@Setter
public class MeasurementCreateRequest {
    private Timestamp timestamp;
    private Double temperature;
    private Double humidity;
    private Integer sensorId;

    public MeasurementCreateRequest() {
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
