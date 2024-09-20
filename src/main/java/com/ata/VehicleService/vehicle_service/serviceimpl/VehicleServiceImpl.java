package com.ata.VehicleService.vehicle_service.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.VehicleService.vehicle_service.Service.VehicleService;
import com.ata.VehicleService.vehicle_service.exception.VehicleNotFoundException;
import com.ata.VehicleService.vehicle_service.vechicleentity.VehicleEntity;
import com.ata.VehicleService.vehicle_service.vehiclerepository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleEntity saveVehicle(VehicleEntity vehicle) {
        logger.info("Saving vehicle: {}", vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<VehicleEntity> getAllVehicles() {
        logger.info("Fetching all vehicles");
        return vehicleRepository.findAll();
    }

    @Override
    public VehicleEntity getVehicleById(Long id) {
        logger.info("Fetching vehicle with id: {}", id);
        return vehicleRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Vehicle not found with id: {}", id);
                    return new VehicleNotFoundException("Vehicle not found with id: " + id);
                });
    }

    @Override
    public void deleteVehicleById(Long id) {
        logger.info("Deleting vehicle with id: {}", id);
        if (!vehicleRepository.existsById(id)) {
            logger.error("Vehicle not found with id: {}", id);
            throw new VehicleNotFoundException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
        logger.info("Vehicle deleted with id: {}", id);
    }
}
