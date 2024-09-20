package com.ata.VehicleService.vehicle_service.vehiclerepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ata.VehicleService.vehicle_service.vechicleentity.VehicleEntity;
@Repository
public interface VehicleRepository extends JpaRepository <VehicleEntity, Long> { 

}
