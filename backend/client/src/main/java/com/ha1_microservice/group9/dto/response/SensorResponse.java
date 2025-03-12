package com.ha1_microservice.group9.dto.response;

import com.ha1_microservice.group9.entity.Sensor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representing the response structure for a sensor, which is returned to the client.
 * Contains information about id, name, location, activity and type.
 */
@Getter
@Setter
public class SensorResponse {
    private int id;
    private String name;
    private String location;
    private boolean active;
    private String type;

    public SensorResponse(Sensor sensor) {
        this.id = sensor.getId();
        this.name = sensor.getName();
        this.location = sensor.getLocation();
        this.active = sensor.isActive();
        this.type = sensor.getType();
    }

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
}
