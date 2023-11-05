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

class ParkingFloor {
    private ParkingSlot[] carSlots;
    private ParkingSlot[] bikeSlots;
    private ParkingSlot[] truckSlots;

    ParkingFloor() {
        carSlots = new ParkingSlot[12];
        bikeSlots = new ParkingSlot[15];
        truckSlots = new ParkingSlot[3];

        for (int i = 0; i < 12; i++) {
            carSlots[i] = new ParkingSlot();
        }

        for (int i = 0; i < 15; i++) {
            bikeSlots[i] = new ParkingSlot();
        }

        for (int i = 0; i < 3; i++) {
            truckSlots[i] = new ParkingSlot();
        }
    }

    void parkCar(Car car) throws Exception {
        parkInSlots(carSlots, car, "Car");
    }

    void parkBike(Bike bike) throws Exception {
        parkInSlots(bikeSlots, bike, "Bike");
    }

    void parkTruck(Truck truck) throws Exception {
        parkInSlots(truckSlots, truck, "Truck");
    }

    private void parkInSlots(ParkingSlot[] slots, Vehicle vehicle, String vehicleType) throws Exception {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                slot.parkVehicle(vehicle);
                System.out.println(vehicleType + " parked successfully.");
                return;
            }
        }
        throw new Exception("No available slots for " + vehicleType + ".");
    }

    Vehicle retrieveVehicle(int slotNumber, int exitTime) {
        if (slotNumber >= 0 && slotNumber < carSlots.length && carSlots[slotNumber].isOccupied()) {
            Vehicle vehicle = carSlots[slotNumber].retrieveVehicle();
            System.out.println("Car retrieved successfully from Slot " + slotNumber);
            return vehicle;
        } else if (slotNumber >= 0 && slotNumber < bikeSlots.length && bikeSlots[slotNumber].isOccupied()) {
            Vehicle vehicle = bikeSlots[slotNumber].retrieveVehicle();
            System.out.println("Bike retrieved successfully from Slot " + slotNumber);
            return vehicle;
        } else if (slotNumber >= 0 && slotNumber < truckSlots.length && truckSlots[slotNumber].isOccupied()) {
            Vehicle vehicle = truckSlots[slotNumber].retrieveVehicle();
            System.out.println("Truck retrieved successfully from Slot " + slotNumber);
            return vehicle;
        }
        System.out.println("Invalid slot number or no vehicle parked at this slot.");
        return null;
    }
}

class ParkingLot {
    private ParkingFloor[] floors;

    ParkingLot(int floorsCount) {
        floors = new ParkingFloor[floorsCount];
        for (int i = 0; i < floorsCount; i++) {
            floors[i] = new ParkingFloor();
        }
    }

    void parkCar(Car car) throws Exception {
        floors[0].parkCar(car);
    }

    void parkBike(Bike bike) throws Exception {
        floors[0].parkBike(bike);
    }

    void parkTruck(Truck truck) throws Exception {
        floors[0].parkTruck(truck);
    }

    Vehicle retrieveVehicle(int floorNumber, int slotNumber, int exitTime) {
        if (floorNumber >= 0 && floorNumber < floors.length) {
            return floors[floorNumber].retrieveVehicle(slotNumber, exitTime);
        } else {
            System.out.println("Invalid floor number.");
            return null;
        }
    }
}

public class OopProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize parking lot with 1 floor
        ParkingLot parkingLot = new ParkingLot(1);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Park a car");
            System.out.println("2. Park a bike");
            System.out.println("3. Park a truck");
            System.out.println("4. Retrieve a vehicle");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character after reading the choice

            switch (choice) {
                case 1:
                    System.out.println("Enter Num plate for car:");
                    String carNumPlate = sc.nextLine();
                    System.out.println("Enter Model for car:");
                    String carModel = sc.nextLine();
                    Car car = new Car(carNumPlate, carModel);
                    try {
                        parkingLot.parkCar(car);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Enter Num plate for bike:");
                    String bikeNumPlate = sc.nextLine();
                    System.out.println("Enter Model for bike:");
                    String bikeModel = sc.nextLine();
                    Bike bike = new Bike(bikeNumPlate, bikeModel);
                    try {
                        parkingLot.parkBike(bike);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Enter Num plate for truck:");
                    String truckNumPlate = sc.nextLine();
                    System.out.println("Enter Model for truck:");
                    String truckModel = sc.nextLine();
                    Truck truck = new Truck(truckNumPlate, truckModel);
                    try {
                        parkingLot.parkTruck(truck);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Enter Slot Number:");
                    int slotNumber = sc.nextInt();
                    System.out.println("Enter Exit Time:");
                    int exitTime = sc.nextInt();
                    Vehicle retrievedVehicle = parkingLot.retrieveVehicle(0, slotNumber, exitTime);
                    if (retrievedVehicle != null) {
                        System.out.println("Retrieved Vehicle Details - Num Plate: " + retrievedVehicle.numPlate +
                                ", Model: " + retrievedVehicle.model);
                    }
                    break;

                case 5:
                    System.out.println("Exiting the parking management system. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
