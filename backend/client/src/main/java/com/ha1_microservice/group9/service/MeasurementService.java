package com.ha1_microservice.group9.service;

import com.ha1_microservice.group9.dto.createRequest.MeasurementCreateRequest;
import com.ha1_microservice.group9.dto.response.MeasurementResponse;
import com.ha1_microservice.group9.entity.Measurement;
import com.ha1_microservice.group9.entity.Sensor;
import com.ha1_microservice.group9.repository.MeasurementRepository;
import com.ha1_microservice.group9.repository.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing measurements.
 * Offers methods for creating and retrieving measurements.
 */
@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;


    /**
     * Constructor for MeasurementService, which uses the MeasurmentRepository.
     * @param measurementRepository repository for managing the measurement-entities
     */
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }


    /**
     * Creation of new measurement based on the given request data.
     * @param request MeasurementCreateRequest-Object with the details of the new measurement
     * @return MeasurementResponse, which presents the saved measurement
     */

    public MeasurementResponse createMeasurement(MeasurementCreateRequest request) {

        Optional<Sensor> sensorOptional = sensorRepository.findById(Long.valueOf(request.getSensorId()));

        if (sensorOptional.isEmpty()) {
            return null;
        }

        Sensor sensor = sensorOptional.get();

        Measurement measurement = new Measurement();
        measurement.setTimestamp(request.getTimestamp());
        measurement.setTemperature(request.getTemperature());
        measurement.setHumidity(request.getHumidity());
        measurement.setSensor(sensor);

        Measurement savedMeasurement = measurementRepository.save(measurement);

        return new MeasurementResponse(savedMeasurement);
    }

    /**
     * Retrieves all saved measurements and returns it as a list of MeasurementResponse-Objects.
     * @return list of all measurements in the MeasurementResponse format.
     */
    public List<MeasurementResponse> getAllMeasurements() {
        List<Measurement> measurements = measurementRepository.findAll();
        return measurements.stream().map(MeasurementResponse::new).collect(Collectors.toList());
    }

    public MeasurementResponse getMeasurementById(long measurementId) {
        Optional<Measurement> measurement = measurementRepository.findById(measurementId);

        return measurement.map(MeasurementResponse::new).orElse(null);
    }
    public MeasurementResponse updateMeasurement(long measurementId, MeasurementCreateRequest request) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);

        if (optionalMeasurement.isEmpty()) {
            return null;
        }

        Measurement measurement = optionalMeasurement.get();
        measurement.setTemperature(request.getTemperature());
        measurement.setHumidity(request.getHumidity());
        measurement.setTimestamp(request.getTimestamp());

        measurementRepository.save(measurement);

        return new MeasurementResponse(measurement);
    }

    public boolean deleteMeasurementById(long measurementId) {
        Optional<Measurement> measurement = measurementRepository.findById(measurementId);
        if (measurement.isPresent()) {
            measurementRepository.delete(measurement.get());
            return true;
        }
        return false;
    }
}
