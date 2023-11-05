import java.util.Scanner;

class Vehicle {
    String numPlate;
    String model;

    Vehicle(String numPlate, String model) {
        this.numPlate = numPlate;
        this.model = model;
    }
}

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

class ParkedVehicle {
    Vehicle vehicle;
    int entryTime;

    ParkedVehicle(Vehicle vehicle, int entryTime) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
    }
}

class ParkingSlot {
    private Vehicle vehicle;

    void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    Vehicle retrieveVehicle() {
        Vehicle parkedVehicle = this.vehicle;
        this.vehicle = null;
        return parkedVehicle;
    }

    boolean isOccupied() {
        return this.vehicle != null;
    }
}

class ParkingFloor {
    private ParkingSlot[] slots;
    private ParkedVehicle[] parkedVehicles;

    ParkingFloor(int carSlots, int bikeSlots, int truckSlots) {
        int totalSlots = carSlots + bikeSlots + truckSlots;
        slots = new ParkingSlot[totalSlots];
        parkedVehicles = new ParkedVehicle[totalSlots];
        for (int i = 0; i < totalSlots; i++) {
            slots[i] = new ParkingSlot();
            parkedVehicles[i] = null;
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

    void parkVehicle(Vehicle vehicle, int entryTime, int carSlots, int bikeSlots, int truckSlots) throws Exception {
        int slotIndex = findAvailableSlot(carSlots, bikeSlots, truckSlots);
        if (slotIndex != -1) {
            slots[slotIndex].parkVehicle(vehicle);
            parkedVehicles[slotIndex] = new ParkedVehicle(vehicle, entryTime);
            System.out.println("Vehicle parked successfully in Slot " + slotIndex);
        } else {
            throw new Exception("Parking floor is full. Please choose another floor.");
        }
    }

    void retrieveVehicle(int slotIndex, int exitTime) {
        if (slotIndex >= 0 && slotIndex < parkedVehicles.length && parkedVehicles[slotIndex] != null) {
            ParkedVehicle parkedVehicle = parkedVehicles[slotIndex];
            int entryTime = parkedVehicle.entryTime;
            Vehicle vehicle = parkedVehicle.vehicle;

            int hours = exitTime - entryTime;
            int payment = calculatePayment(entryTime, exitTime, vehicle);

            System.out.println("Vehicle retrieved successfully.");
            System.out.println("Vehicle Details - Num Plate: " + vehicle.numPlate + ", Model: " + vehicle.model);
            System.out.println("Entry Time: " + entryTime + ", Exit Time: " + exitTime);
            System.out.println("Total Parking Duration: " + hours + " hours");
            System.out.println("Payment Fee: $" + payment);
        } else {
            System.out.println("Invalid slot index or no vehicle parked at this slot.");
        }
    }

    private int calculatePayment(int entryTime, int exitTime, Vehicle vehicle) {
        int basePrice = 0;
        int hourlyRate = 0;
        int additionalHourlyRate = 0;

        if (vehicle instanceof Bike) {
            basePrice = 20;
            hourlyRate = 20;
            additionalHourlyRate = 1000;
        } else if (vehicle instanceof Car) {
            basePrice = 50;
            hourlyRate = 50;
            additionalHourlyRate = 1000;
        } else if (vehicle instanceof Truck) {
            basePrice = 100;
            hourlyRate = 100;
            additionalHourlyRate = 1000;
        }

        int hours = exitTime - entryTime;
        int payment = basePrice + (hours * hourlyRate);

        if (hours > 4) {
            payment += ((hours - 4) * additionalHourlyRate);
        }

        return payment;
    }
}

class ParkingLot {
    private ParkingFloor[] floors;

    ParkingLot(int floorsCount, int carSlots, int bikeSlots, int truckSlots) {
        floors = new ParkingFloor[floorsCount];
        for (int i = 0; i < floorsCount; i++) {
            floors[i] = new ParkingFloor(carSlots, bikeSlots, truckSlots);
        }
    }

    void parkVehicle(Vehicle vehicle, int entryTime, int floorNumber, int carSlots, int bikeSlots, int truckSlots) throws Exception {
        if (floorNumber >= 0 && floorNumber < floors.length) {
            floors[floorNumber].parkVehicle(vehicle, entryTime, carSlots, bikeSlots, truckSlots);
        } else {
            throw new Exception("Invalid floor number.");
        }
    }

    void retrieveVehicle(int floorNumber, int slotIndex, int exitTime) {
        if (floorNumber >= 0 && floorNumber < floors.length) {
            floors[floorNumber].retrieveVehicle(slotIndex, exitTime);
        } else {
            System.out.println("Invalid floor number.");
        }
    }
}

public class OopProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize parking lot with 3 floors, each having 12 car slots, 15 bike slots, and 3 truck slots
        ParkingLot parkingLot = new ParkingLot(3, 12, 15, 3);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Park a vehicle");
            System.out.println("2. Retrieve a vehicle");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character after reading the choice

            switch (choice) {
                case 1:
                    System.out.println("Enter type of vehicle: (c for car, t for truck, b for bike)");
                    String type = sc.nextLine();

                    System.out.println("Enter Num plate:");
                    String numPlate = sc.nextLine();
                    System.out.println("Enter Model:");
                    String model = sc.nextLine();

                    System.out.println("Enter Entry Time (in hours):");
                    int entryTime = sc.nextInt();

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
                                continue;
                        }

                        // Park the vehicle with entry time
                        parkingLot.parkVehicle(vehicle, entryTime, floorNumber, 12, 15, 3);
                        System.out.println("Vehicle parked successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Enter Floor Number (0, 1, 2):");
                    int floorNum = sc.nextInt();

                    System.out.println("Enter Slot Number:");
                    int slotIndex = sc.nextInt();

                    System.out.println("Enter Exit Time (in hours):");
                    int exitTime = sc.nextInt();

                    // Retrieve the vehicle and calculate payment
                    parkingLot.retrieveVehicle(floorNum, slotIndex, exitTime);
                    break;

                case 3:
                    System.out.println("Exiting the parking management system. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
