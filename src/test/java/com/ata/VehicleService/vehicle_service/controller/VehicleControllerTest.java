package com.ata.VehicleService.vehicle_service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ata.VehicleService.vehicle_service.Service.VehicleService;
import com.ata.VehicleService.vehicle_service.contoller.VehicleController;
import com.ata.VehicleService.vehicle_service.vechicleentity.VehicleEntity;

class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleService.saveVehicle(vehicle)).thenReturn(vehicle);

        ResponseEntity<VehicleEntity> response = vehicleController.createVehicle(vehicle);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicle, response.getBody());
        verify(vehicleService, times(1)).saveVehicle(vehicle);
    }

    @Test
    void testGetAllVehicles() {
        List<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(new VehicleEntity());
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        ResponseEntity<List<VehicleEntity>> response = vehicleController.getAllVehicles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicles, response.getBody());
        verify(vehicleService, times(1)).getAllVehicles();
    }

    @Test
    void testGetAllVehicles_NoContent() {
        when(vehicleService.getAllVehicles()).thenReturn(new ArrayList<>());

        ResponseEntity<List<VehicleEntity>> response = vehicleController.getAllVehicles();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vehicleService, times(1)).getAllVehicles();
    }

    @Test
    void testGetVehicleById() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleService.getVehicleById(1L)).thenReturn(vehicle);

        ResponseEntity<VehicleEntity> response = vehicleController.getVehicleById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicle, response.getBody());
        verify(vehicleService, times(1)).getVehicleById(1L);
    }

    @Test
    void testDeleteVehicleById() {
        doNothing().when(vehicleService).deleteVehicleById(1L);

        ResponseEntity<String> response = vehicleController.deleteVehicleById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vehicle deleted successfully", response.getBody());
        verify(vehicleService, times(1)).deleteVehicleById(1L);
    }
}
