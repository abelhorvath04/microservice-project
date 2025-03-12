package com.ha1_microservice.group9.controller;

import com.ha1_microservice.group9.dto.createRequest.MeasurementCreateRequest;
import com.ha1_microservice.group9.dto.response.MeasurementResponse;
import com.ha1_microservice.group9.service.MeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Measurement endpoint - Clients can communicate with the system
 */

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public ResponseEntity<MeasurementResponse> createMeasurement(
            @RequestBody MeasurementCreateRequest request) {
        MeasurementResponse response = measurementService.createMeasurement(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MeasurementResponse>> getAllMeasurements() {
        List<MeasurementResponse> responses = measurementService.getAllMeasurements();
        if (responses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{measurementId}")
    public ResponseEntity<MeasurementResponse> getMeasurementById(@PathVariable long measurementId) {
        MeasurementResponse measurementResponse = measurementService.getMeasurementById(measurementId);
        if (measurementResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(measurementResponse);
    }

    @PutMapping("/{measurementId}")
    public ResponseEntity<MeasurementResponse> updateMeasurement(@PathVariable long measurementId, @RequestBody MeasurementCreateRequest request) {
        MeasurementResponse updatedMeasurement = measurementService.updateMeasurement(measurementId, request);
        if (updatedMeasurement == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedMeasurement);
    }

    @DeleteMapping("/{measurementId}")
    public ResponseEntity<Void> deleteMeasurementById(@PathVariable long measurementId) {
        boolean isDeleted = measurementService.deleteMeasurementById(measurementId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
