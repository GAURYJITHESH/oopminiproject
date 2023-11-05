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

class ParkedVehicle {
    Vehicle vehicle;
    int entryTime;
    int slotNumber;

    ParkedVehicle(Vehicle vehicle, int entryTime, int slotNumber) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.slotNumber = slotNumber;
    }
}

class ParkingFloor {
    private ParkingSlot[] carSlots;
    private ParkingSlot[] bikeSlots;
    private ParkingSlot[] truckSlots;
    private ParkedVehicle[] parkedVehicles;

    ParkingFloor(int carSlotsCount, int bikeSlotsCount, int truckSlotsCount) {
        carSlots = new ParkingSlot[carSlotsCount];
        bikeSlots = new ParkingSlot[bikeSlotsCount];
        truckSlots = new ParkingSlot[truckSlotsCount];
        parkedVehicles = new ParkedVehicle[carSlotsCount + bikeSlotsCount + truckSlotsCount];

        for (int i = 0; i < carSlotsCount; i++) {
            carSlots[i] = new ParkingSlot();
        }

        for (int i = 0; i < bikeSlotsCount; i++) {
            bikeSlots[i] = new ParkingSlot();
        }

        for (int i = 0; i < truckSlotsCount; i++) {
            truckSlots[i] = new ParkingSlot();
        }
    }

    void parkVehicle(Vehicle vehicle, String type, int entryTime) throws Exception {
        int slotNumber = -1;
        switch (type) {
            case "c":
                slotNumber = parkInSlots(carSlots, vehicle, "Car");
                break;
            case "b":
                slotNumber = parkInSlots(bikeSlots, vehicle, "Bike");
                break;
            case "t":
                slotNumber = parkInSlots(truckSlots, vehicle, "Truck");
                break;
            default:
                throw new Exception("Invalid vehicle type.");
        }
        if (slotNumber != -1) {
            parkedVehicles[slotNumber] = new ParkedVehicle(vehicle, entryTime, slotNumber);
            System.out.println(vehicle.model + " parked successfully in Slot " + slotNumber);
        } else {
            throw new Exception("No available slots for the vehicle.");
        }
    }

    private int parkInSlots(ParkingSlot[] slots, Vehicle vehicle, String vehicleType) {
        for (int i = 0; i < slots.length; i++) {
            if (!slots[i].isOccupied()) {
                slots[i].parkVehicle(vehicle);
                return i;
            }
        }
        return -1; // No available slots
    }

    void retrieveVehicle(int slotNumber, int exitTime) {
        if (slotNumber >= 0 && slotNumber < parkedVehicles.length && parkedVehicles[slotNumber] != null) {
            ParkedVehicle parkedVehicle = parkedVehicles[slotNumber];
            int entryTime = parkedVehicle.entryTime;
            Vehicle vehicle = parkedVehicle.vehicle;

            int hours = (exitTime - entryTime) / 100; // Calculate hours difference in military format
            int payment = calculatePayment(hours, vehicle);

            System.out.println("Vehicle retrieved successfully.");
            System.out.println("Vehicle Details - Num Plate: " + vehicle.numPlate + ", Model: " + vehicle.model);
            System.out.println("Entry Time: " + entryTime + ", Exit Time: " + exitTime);
            System.out.println("Total Parking Duration: " + hours + " hours");
            System.out.println("Payment Fee: $" + payment);
        } else {
            System.out.println("Invalid slot number or no vehicle parked at this slot.");
        }
    }

    private int calculatePayment(int hours, Vehicle vehicle) {
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

        int payment = basePrice + (hours * hourlyRate);

        if (hours > 4) {
            payment += ((hours - 4) * additionalHourlyRate);
        }

        return payment;
    }
}

class ParkingLot {
    private ParkingFloor[] floors;

    ParkingLot(int floorsCount) {
        floors = new ParkingFloor[floorsCount];
        for (int i = 0; i < floorsCount; i++) {
            floors[i] = new ParkingFloor(12, 15, 3);
        }
    }

    void parkVehicle(Vehicle vehicle, String type, int floorNumber, int entryTime) throws Exception {
        if (floorNumber >= 0 && floorNumber < floors.length) {
            floors[floorNumber].parkVehicle(vehicle, type, entryTime);
        } else {
            throw new Exception("Invalid floor number.");
        }
    }

    void retrieveVehicle(int floorNumber, int slotNumber, int exitTime) {
        if (floorNumber >= 0 && floorNumber < floors.length) {
            floors[floorNumber].retrieveVehicle(slotNumber, exitTime);
        } else {
            System.out.println("Invalid floor number.");
        }
    }
}

public class OopProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize parking lot with 3 floors
        ParkingLot parkingLot = new ParkingLot(3);

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

                    System.out.println("Enter Entry Time (in military hours format, e.g., 1500):");
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
                        parkingLot.parkVehicle(vehicle, type, floorNumber, entryTime);
                        System.out.println("Vehicle parked successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Enter Floor Number (0, 1, 2):");
                    int floorNum = sc.nextInt();

                    System.out.println("Enter Slot Number:");
                    int slotNumber = sc.nextInt();

                    System.out.println("Enter Exit Time (in military hours format, e.g., 1700):");
                    int exitTime = sc.nextInt();

                    // Retrieve the vehicle and calculate payment
                    parkingLot.retrieveVehicle(floorNum, slotNumber, exitTime);
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
