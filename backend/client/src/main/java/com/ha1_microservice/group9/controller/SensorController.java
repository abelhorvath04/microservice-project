package com.ha1_microservice.group9.controller;

import com.ha1_microservice.group9.dto.createRequest.MeasurementCreateRequest;
import com.ha1_microservice.group9.dto.createRequest.SensorCreateRequest;
import com.ha1_microservice.group9.dto.response.MeasurementResponse;
import com.ha1_microservice.group9.dto.response.SensorResponse;
import com.ha1_microservice.group9.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Sensor enpoint - Clients can communicate with the system
 */
@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<SensorResponse> createSensor(
            @RequestBody SensorCreateRequest request) {
        SensorResponse response = sensorService.createSensor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SensorResponse>> getAllSensors() {
        List<SensorResponse> responses = sensorService.getAllSensors();
        if (responses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorResponse> getSensorById(@PathVariable long sensorId) {
        SensorResponse sensorResponse = sensorService.getSensorById(sensorId);
        if (sensorResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensorResponse);
    }

    @PutMapping("/{sensorId}")
    public ResponseEntity<SensorResponse> updateSensor(@PathVariable long sensorId, @RequestBody SensorCreateRequest request) {
        SensorResponse updatedSensor = sensorService.updateSensor(sensorId, request);
        if (updatedSensor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedSensor);
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Void> deleteSensorById(@PathVariable long sensorId) {
        boolean isDeleted = sensorService.deleteSensorById(sensorId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
