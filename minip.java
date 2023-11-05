import java.util.Scanner;

class Vehicle {
    String numPlate;
    String model;
    int entryTime;

    Vehicle(String numPlate, String model, int entryTime) {
        this.numPlate = numPlate;
        this.model = model;
        this.entryTime = entryTime;
    }
}

class Car extends Vehicle {
    Car(String numPlate, String model, int entryTime) {
        super(numPlate, model, entryTime);
    }
}

class Bike extends Vehicle {
    Bike(String numPlate, String model, int entryTime) {
        super(numPlate, model, entryTime);
    }
}

class Truck extends Vehicle {
    Truck(String numPlate, String model, int entryTime) {
        super(numPlate, model, entryTime);
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

    void parkCar(Car car, int slotNumber) throws Exception {
        parkInSlot(carSlots, car, slotNumber, "Car");
    }

    void parkBike(Bike bike, int slotNumber) throws Exception {
        parkInSlot(bikeSlots, bike, slotNumber, "Bike");
    }

    void parkTruck(Truck truck, int slotNumber) throws Exception {
        parkInSlot(truckSlots, truck, slotNumber, "Truck");
    }

    private void parkInSlot(ParkingSlot[] slots, Vehicle vehicle, int slotNumber, String vehicleType) throws Exception {
        if (slotNumber >= 0 && slotNumber < slots.length && !slots[slotNumber].isOccupied()) {
            slots[slotNumber].parkVehicle(vehicle);
            System.out.println(vehicleType + " parked successfully in Slot " + slotNumber);
        } else {
            throw new Exception("Invalid slot number or slot already occupied for " + vehicleType + ".");
        }
    }

    Vehicle retrieveVehicle(int slotNumber, int exitTime, String numPlate) {
        ParkingSlot[] slots = null;
        String vehicleType = "";

        if (slotNumber >= 0 && slotNumber < carSlots.length && carSlots[slotNumber].isOccupied()) {
            slots = carSlots;
            vehicleType = "Car";
        } else if (slotNumber >= 0 && slotNumber < bikeSlots.length && bikeSlots[slotNumber].isOccupied()) {
            slots = bikeSlots;
            vehicleType = "Bike";
        } else if (slotNumber >= 0 && slotNumber < truckSlots.length && truckSlots[slotNumber].isOccupied()) {
            slots = truckSlots;
            vehicleType = "Truck";
        }

        if (slots != null) {
            for (ParkingSlot slot : slots) {
                if (slot.isOccupied() && slot.retrieveVehicle().numPlate.equalsIgnoreCase(numPlate)) {
                    int hours = (exitTime - slot.retrieveVehicle().entryTime) / 100; // Assuming entry time is always before 1500 hours
                    int basePrice = (vehicleType.equals("Bike")) ? 20 : ((vehicleType.equals("Car")) ? 50 : 100);
                    int additionalFee = (hours <= 4) ? (hours * 20) : 1000;
                    int totalPayment = basePrice + additionalFee;
                    System.out.println(vehicleType + " with Num Plate " + numPlate + " retrieved successfully from Slot " + slotNumber);
                    System.out.println("Total Payment: Rs. " + totalPayment);
                    return slot.retrieveVehicle();
                }
            }
            System.out.println("Invalid number plate or no matching vehicle found at this slot.");
        } else {
            System.out.println("Invalid slot number or no vehicle parked at this slot.");
        }
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

    void parkCar(Car car, int floorNumber, int slotNumber) throws Exception {
        floors[floorNumber].parkCar(car, slotNumber);
    }

    void parkBike(Bike bike, int floorNumber, int slotNumber) throws Exception {
        floors[floorNumber].parkBike(bike, slotNumber);
    }

    void parkTruck(Truck truck, int floorNumber, int slotNumber) throws Exception {
        floors[floorNumber].parkTruck(truck, slotNumber);
    }

    Vehicle retrieveVehicle(int floorNumber, int slotNumber, int exitTime, String numPlate) {
        if (floorNumber >= 0 && floorNumber < floors.length) {
            return floors[floorNumber].retrieveVehicle(slotNumber, exitTime, numPlate);
        } else {
            System.out.println("Invalid floor number.");
            return null;
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
                    System.out.println("Enter Entry Time (in military hours):");
                    int carEntryTime = sc.nextInt();
                    Car car = new Car(carNumPlate, carModel, carEntryTime);
                    try {
                        System.out.println("Enter Floor Number (0, 1, 2):");
                        int floorNumber = sc.nextInt();
                        System.out.println("Enter Slot Number (0-11):");
                        int slotNumber = sc.nextInt();
                        parkingLot.parkCar(car, floorNumber, slotNumber);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Enter Num plate for bike:");
                    String bikeNumPlate = sc.nextLine();
                    System.out.println("Enter Model for bike:");
                    String bikeModel = sc.nextLine();
                    System.out.println("Enter Entry Time (in military hours):");
                    int bikeEntryTime = sc.nextInt();
                    Bike bike = new Bike(bikeNumPlate, bikeModel, bikeEntryTime);
                    try {
                        System.out.println("Enter Floor Number (0, 1, 2):");
                        int floorNumber = sc.nextInt();
                        System.out.println("Enter Slot Number (0-14):");
                        int slotNumber = sc.nextInt();
                        parkingLot.parkBike(bike, floorNumber, slotNumber);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Enter Num plate for truck:");
                    String truckNumPlate = sc.nextLine();
                    System.out.println("Enter Model for truck:");
                    String truckModel = sc.nextLine();
                    System.out.println("Enter Entry Time (in military hours):");
                    int truckEntryTime = sc.nextInt();
                    Truck truck = new Truck(truckNumPlate, truckModel, truckEntryTime);
                    try {
                        System.out.println("Enter Floor Number (0, 1, 2):");
                        int floorNumber = sc.nextInt();
                        System.out.println("Enter Slot Number (0-2):");
                        int slotNumber = sc.nextInt();
                        parkingLot.parkTruck(truck, floorNumber, slotNumber);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Enter Floor Number (0, 1, 2):");
                    int floorNumber = sc.nextInt();
                    System.out.println("Enter Slot Number:");
                    int slotNumber = sc.nextInt();
                    System.out.println("Enter Exit Time (in military hours):");
                    int exitTime = sc.nextInt();
                    sc.nextLine(); // Consume the newline character after reading the exit time
                    System.out.println("Enter Num Plate of the vehicle:");
                    String numPlate = sc.nextLine();
                    parkingLot.retrieveVehicle(floorNumber, slotNumber, exitTime, numPlate);
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
