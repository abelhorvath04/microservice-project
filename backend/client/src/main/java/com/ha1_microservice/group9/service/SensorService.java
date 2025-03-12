package com.ha1_microservice.group9.service;

import com.ha1_microservice.group9.dto.createRequest.MeasurementCreateRequest;
import com.ha1_microservice.group9.dto.createRequest.SensorCreateRequest;
import com.ha1_microservice.group9.dto.response.MeasurementResponse;
import com.ha1_microservice.group9.dto.response.SensorResponse;
import com.ha1_microservice.group9.entity.Measurement;
import com.ha1_microservice.group9.entity.Sensor;
import com.ha1_microservice.group9.repository.SensorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing sensors.
 * Offers methods for creating and retrieving sensor-data.
 */
@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    /**
     * Constructor from SensorService, which uses the SensorRepository.
     * @param sensorRepository  SensorRepository for managing sensor entities.
     */
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    /**
     * Creation of a new sensor based on the given Request-Data.
     * @param request SensorCreateRequest-Object with the details to the new sensor.
     * @return SensorResponse-Object, which presents the saved sensor.
     */
    public SensorResponse createSensor(SensorCreateRequest request) {
        Sensor sensor = new Sensor();
        sensor.setName(request.getName());
        sensor.setLocation(request.getLocation());
        sensor.setActive(request.isActive());
        sensor.setType(request.getType());

        Sensor savedSensor = sensorRepository.save(sensor);

        return new SensorResponse(savedSensor);
    }

    /**
     * Retrieves all saved sensors and returns them as a list of SensorResponse-Objects.
     * @return a list of sensors in the SensorResponse format.
     */
    public List<SensorResponse> getAllSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensors.stream().map(SensorResponse::new).collect(Collectors.toList());
    }

    public SensorResponse getSensorById(long sensorId) {
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);

        return sensor.map(SensorResponse::new).orElse(null);
    }

    public SensorResponse updateSensor(long sensorId, SensorCreateRequest request) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(sensorId);

        if (optionalSensor.isEmpty()) {
            return null;
        }

        Sensor sensor = optionalSensor.get();
        sensor.setName(request.getName());
        sensor.setLocation(request.getLocation());
        sensor.setActive(request.isActive());
        sensor.setType(request.getType());

        sensorRepository.save(sensor);

        return new SensorResponse(sensor);
    }

    public boolean deleteSensorById(long sensorId) {
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        if (sensor.isPresent()) {
            sensorRepository.delete(sensor.get());
            return true;
        }
        return false;
    }

}
