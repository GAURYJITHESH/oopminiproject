import java.util.Scanner;

// Base class for all vehicles
class Vehicle {
    String numPlate;
    String model;

    // Constructor to initialize vehicle details
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

    // Method to park a vehicle in the slot
    void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // Method to retrieve parked vehicle
    Vehicle retrieveVehicle() {
        Vehicle parkedVehicle = this.vehicle;
        this.vehicle = null; // Clear the slot after retrieving the vehicle
        return parkedVehicle;
    }

    // Method to check if the slot is occupied
    boolean isOccupied() {
        return this.vehicle != null;
    }
}

// ParkingLot class to manage multiple parking slots
class ParkingLot {
    private ParkingSlot[] slots;

    // Constructor to initialize parking slots
    ParkingLot(int capacity) {
        slots = new ParkingSlot[capacity];
        for (int i = 0; i < capacity; i++) {
            slots[i] = new ParkingSlot();
        }
    }

    // Method to find the first available slot for parking
    int findAvailableSlot() {
        for (int i = 0; i < slots.length; i++) {
            if (!slots[i].isOccupied()) {
                return i;
            }
        }
        return -1; // No available slots
    }

    // Method to park a vehicle in the parking lot
    void parkVehicle(Vehicle vehicle) throws Exception {
        int slotIndex = findAvailableSlot();
        if (slotIndex != -1) {
            slots[slotIndex].parkVehicle(vehicle);
        } else {
            throw new Exception("Parking lot is full.");
        }
    }
}

// Main class for the parking management system
public class OopProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize parking lot with 20 slots
        ParkingLot parkingLot = new ParkingLot(20);

        System.out.println("Enter type of vehicle: (c for car, t for truck, b for bike)");
        String type = sc.nextLine();

        System.out.println("Enter Num plate:");
        String numPlate = sc.nextLine();
        System.out.println("Enter Model:");
        String model = sc.nextLine();

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
            parkingLot.parkVehicle(vehicle);
            System.out.println("Vehicle parked successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
