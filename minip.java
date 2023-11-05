import java.util.Scanner;

// Base class for all vehicles
class Vehicle {
    String numPlate;
    String model;

    Vehicle(String numPlate, String model) {
        this.numPlate = numPlate;
        this.model = model;
    }
}

// Derived classes for specific vehicle types
class Car extends Vehicle {
    Car(String numPlate, String model) {
        super(numPlate, model);
    }
}

class Bike extends Vehicle {
    Bike(String numPlate, String model) {
        super(numPlate, model);
    }
}

class Truck extends Vehicle {
    Truck(String numPlate, String model) {
        super(numPlate, model);
    }
}

// ParkingSlot class to represent individual parking slots
class ParkingSlot {
    private Vehicle vehicle;

    void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    Vehicle retrieveVehicle() {
        Vehicle parkedVehicle = this.vehicle;
        this.vehicle = null; // Clear the slot after retrieving the vehicle
        return parkedVehicle;
    }

    boolean isOccupied() {
        return this.vehicle != null;
    }
}

// ParkingFloor class to manage parking slots for a specific floor
class ParkingFloor {
    private ParkingSlot[] slots;

    ParkingFloor(int carSlots, int bikeSlots, int truckSlots) {
        int totalSlots = carSlots + bikeSlots + truckSlots;
        slots = new ParkingSlot[totalSlots];
        for (int i = 0; i < totalSlots; i++) {
            slots[i] = new ParkingSlot();
        }
    }

    int findAvailableSlot(int carSlots, int bikeSlots, int truckSlots) {
        for (int i = 0; i < carSlots; i++) {
            if (!slots[i].isOccupied()) {
                return i;
            }
        }
        for (int i = carSlots; i < carSlots + bikeSlots; i++) {
            if (!slots[i].isOccupied()) {
                return i;
            }
        }
        for (int i = carSlots + bikeSlots; i < carSlots + bikeSlots + truckSlots; i++) {
            if (!slots[i].isOccupied()) {
                return i;
            }
        }
        return -1; // No available slots
    }

    void parkVehicle(Vehicle vehicle, int carSlots, int bikeSlots, int truckSlots) throws Exception {
        int slotIndex = findAvailableSlot(carSlots, bikeSlots, truckSlots);
        if (slotIndex != -1) {
            slots[slotIndex].parkVehicle(vehicle);
            System.out.println("Vehicle parked successfully in Slot " + slotIndex);
        } else {
            throw new Exception("Parking floor is full. Please choose another floor.");
        }
    }
}

// ParkingLot class to manage multiple parking floors
class ParkingLot {
    private ParkingFloor[] floors;

    ParkingLot(int floorsCount, int carSlots, int bikeSlots, int truckSlots) {
        floors = new ParkingFloor[floorsCount];
        for (int i = 0; i < floorsCount; i++) {
            floors[i] = new ParkingFloor(carSlots, bikeSlots, truckSlots);
        }
    }

    void parkVehicle(Vehicle vehicle, int floorNumber, int carSlots, int bikeSlots, int truckSlots) throws Exception {
        if (floorNumber >= 0 && floorNumber < floors.length) {
            floors[floorNumber].parkVehicle(vehicle, carSlots, bikeSlots, truckSlots);
        } else {
            throw new Exception("Invalid floor number.");
        }
    }
}

// Main class for the parking management system
public class OopProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize parking lot with 3 floors, each having 12 car slots, 15 bike slots, and 3 truck slots
        ParkingLot parkingLot = new ParkingLot(3, 12, 15, 3);

        System.out.println("Enter type of vehicle: (c for car, t for truck, b for bike)");
        String type = sc.nextLine();

        System.out.println("Enter Num plate:");
        String numPlate = sc.nextLine();
        System.out.println("Enter Model:");
        String model = sc.nextLine();

        System.out.println("Enter Floor Number (0, 1, 2):");
        int floorNumber = sc.nextInt();

        try {
            Vehicle vehicle = null;
            switch (type) {
                case "c":
                    vehicle = new Car(numPlate, model);
                    break;
                case "b":
                    vehicle = new Bike(numPlate, model);
                    break;
                case "t":
                    vehicle = new Truck(numPlate, model);
                    break;
                default:
                    System.out.println("Invalid vehicle type.");
                    return;
            }

            // Park the vehicle
            parkingLot.parkVehicle(vehicle, floorNumber, 12, 15, 3);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
