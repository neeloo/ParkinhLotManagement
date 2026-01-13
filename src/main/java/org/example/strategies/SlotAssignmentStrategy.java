package org.example.strategies;


import org.example.models.ParkingLot;
import org.example.models.ParkingSlot;
import org.example.models.VehicleType;

public interface SlotAssignmentStrategy {
    public ParkingSlot getAvailableSlot(ParkingLot parkingLot, VehicleType type);
}