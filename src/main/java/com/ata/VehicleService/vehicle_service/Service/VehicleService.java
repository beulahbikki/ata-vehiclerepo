package com.ata.VehicleService.vehicle_service.Service;



import java.util.List;

import com.ata.VehicleService.vehicle_service.vechicleentity.VehicleEntity;

public interface VehicleService {
    VehicleEntity saveVehicle(VehicleEntity vehicle);
    List<VehicleEntity> getAllVehicles();
    VehicleEntity getVehicleById(Long id);
    void deleteVehicleById(Long id);
}
