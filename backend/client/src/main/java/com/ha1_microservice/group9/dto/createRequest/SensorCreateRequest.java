package com.ha1_microservice.group9.dto.createRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * Representing data which is necessary for creating a new sensor in the API.
 * Contains information about name, location, activity and the type.
 */
@Getter
@Setter
public class SensorCreateRequest {
    private String name;
    private String location;
    private boolean active;
    private String type;

    public SensorCreateRequest() {
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
