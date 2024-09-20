package com.ata.VehicleService.vehicle_service.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ata.VehicleService.vehicle_service.Service.VehicleService;
import com.ata.VehicleService.vehicle_service.vechicleentity.VehicleEntity;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add")
    public ResponseEntity<VehicleEntity> createVehicle(@RequestBody VehicleEntity vehicle) {
        logger.info("Request to create vehicle: {}", vehicle);
        VehicleEntity createdVehicle = vehicleService.saveVehicle(vehicle);
        logger.info("Vehicle created: {}", createdVehicle);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleEntity>> getAllVehicles() {
        logger.info("Request to get all vehicles");
        List<VehicleEntity> vehicles = vehicleService.getAllVehicles();
        if (vehicles.isEmpty()) {
            logger.info("No vehicles found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Vehicles found: {}", vehicles);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable Long id) {
        logger.info("Request to get vehicle with id: {}", id);
        VehicleEntity vehicle = vehicleService.getVehicleById(id);
        logger.info("Vehicle found: {}", vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable Long id) {
        logger.info("Request to delete vehicle with id: {}", id);
        vehicleService.deleteVehicleById(id);
        logger.info("Vehicle deleted with id: {}", id);
        return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);
    }
}
