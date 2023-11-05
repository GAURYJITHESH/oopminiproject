import java.util.*;

class Vehicle{
	String numPlate;
}
class Car extends Vehicle{
	String modelC;
	Car(){
		Scanner sc = new Scanner(System.in);		
		System.out.println("Enter Num plate:");
		numPlate=sc.nextLine();
		System.out.println("Enter Model:");
		modelC=sc.nextLine();
	}
}
class Bike extends Vehicle{
	String modelB;
	Bike(){
		Scanner sc = new Scanner(System.in);		
		System.out.println("Enter Num plate:");
		numPlate=sc.nextLine();
		System.out.println("Enter Model:");
		modelB=sc.nextLine();
	}
}
class Truck extends Vehicle{
	String modelT;
	Truck(){
		Scanner sc = new Scanner(System.in);		
		System.out.println("Enter Num plate:");
		numPlate=sc.nextLine();
		System.out.println("Enter Model:");
		modelT=sc.nextLine();
	}
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
class p{
Myexceptions parkingfull=new Myexceptions("Slot not available");
void check(int count1,int count2, int count3) throws Myexceptions{
try{
if(count1>10||count2>10||count3>10){
throw parkingfull;
}
System.out.println("Vehicle is parked.");
}
catch(Myexceptions e){
System.out.print(e.getcode());
}
}
}


class OopProject{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		p exc=new p();
		int count1=0;
		int count2=0;
		int count3=0;
		System.out.println("Enter type of vehicle: (c for car, t for truck, b for bike)");
		String type=sc.nextLine();
		switch(type){
			case "c":
				Vehicle car = new Car();
				count1++;
				break;
			case "b":
				Vehicle bike = new Bike();
				break;
				count2++;
			case "t":
				Vehicle truck = new Truck();
				count3++;
				break;
			default:
				System.out.println("invalid");
				return;
		}
		exc.check(count1,count2,count3);

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
