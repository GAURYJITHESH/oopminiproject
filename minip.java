import java.util.*;

class Vehicle{
	String numPlate;
}
class Car extends Vehicle{
	String modelC;
}
class Bike extends Vehicle{
	String modelB;
}
class Truck extends Vehicle{
	String modelT;
}

class Building{
	int floor;
	int slot;
}

class Park extends Building{
    int entryTime;
    Park(int time){
        entryTime=time;
    }
   
    void entryy(){
        System.out.println("enter the entry time");
        entryTime=sc.nextInt();
    }

}
