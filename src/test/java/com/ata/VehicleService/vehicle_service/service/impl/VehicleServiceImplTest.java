package com.ata.VehicleService.vehicle_service.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ata.VehicleService.vehicle_service.exception.VehicleNotFoundException;
import com.ata.VehicleService.vehicle_service.serviceimpl.VehicleServiceImpl;
import com.ata.VehicleService.vehicle_service.vechicleentity.VehicleEntity;
import com.ata.VehicleService.vehicle_service.vehiclerepository.VehicleRepository;

class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        VehicleEntity savedVehicle = vehicleService.saveVehicle(vehicle);
        assertEquals(vehicle, savedVehicle);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void testGetAllVehicles() {
        List<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(new VehicleEntity());
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<VehicleEntity> retrievedVehicles = vehicleService.getAllVehicles();
        assertEquals(vehicles, retrievedVehicles);
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void testGetVehicleById_Success() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        VehicleEntity retrievedVehicle = vehicleService.getVehicleById(1L);
        assertEquals(vehicle, retrievedVehicle);
        verify(vehicleRepository, times(1)).findById(1L);
    }

    @Test
    void testGetVehicleById_NotFound() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> {
            vehicleService.getVehicleById(1L);
        });
        verify(vehicleRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteVehicleById_Success() {
        when(vehicleRepository.existsById(1L)).thenReturn(true);
        doNothing().when(vehicleRepository).deleteById(1L);

        vehicleService.deleteVehicleById(1L);
        verify(vehicleRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteVehicleById_NotFound() {
        when(vehicleRepository.existsById(1L)).thenReturn(false);

        assertThrows(VehicleNotFoundException.class, () -> {
            vehicleService.deleteVehicleById(1L);
        });
        verify(vehicleRepository, never()).deleteById(1L);
    }
}
