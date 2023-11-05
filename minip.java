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
	Scanner sc = new Scanner(System.in);
        System.out.println("enter the entry time");
        entryTime=sc.nextInt();
    }

}

class Retrieve extends Park{
	Scanner sc=new Scanner(System.in);
    int exitTime;
    int payment=20;
    int hours;
    Retrieve(int time,int ET){
        super(time);
        exitTime=ET;
    }

      void retrievee(){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the exit time");
        exitTime=sc.nextInt();
    }
 
    void totalpayment(){
    hours=exitTime-entryTime;
    if(hours< 1){
        payment=20;
        System.out.println("the payment is"+payment);
    }
    else if(1<hours && hours<2){
       payment+=40;
       System.out.println("the payment is"+payment);
    }
    else if(2 < hours && hours < 4){
        payment+=60;
        System.out.println("the payment is"+payment);
    }
    else{
        payment+=1000;
        System.out.println("the payment is"+payment);
    }

    }
}

class OopProject{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter type of vehicle: (c for car, t for truck, b for bike)");
		String type=sc.nextLine();
		switch(type){
			case "c":
				Vehicle car = new Car();
				break;
			case "b":
				Vehicle bike = new Bike();
				break;
			case "t":
				Vehicle truck = new Truck();
				break;
			default:
				System.out.println("invalid");
				return;
		}

	}
}

class Myexceptions extends Exception{
    private String errorcode;
    Myexceptions(String err){
        errorcode=err;
    }
    String getcode(){
        return errorcode;
    }
}
