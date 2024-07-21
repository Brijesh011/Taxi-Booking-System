import java.util.*;

public class Taxi {

    int taxicount = 0;
    int id;
    boolean booked;
    char currentspot;
    int freetime; 
    int totalearnings;
    List<String> trips;

    public Taxi(){
        id = 0;
        booked = false;
        currentspot = 'A';
        freetime = 0;
        totalearnings = 0;
        trips = new ArrayList<String>();
        taxicount = taxicount + 1;
        id = taxicount;
    }

    public void setDetails(boolean booked,char currentspot,int freetime,int totalearnings,String tripdetails ){
        this.booked = booked;
        this.currentspot = currentspot;
        this.freetime = freetime;
        this.totalearnings = totalearnings;
        this.trips.add(tripdetails);

    }

    public void printDetails(){

        System.out.println("Taxi "+this.id + "Earnings" + this.totalearnings);
        System.out.println("TaxiId    BookingId    CustomerId from  to PickupTime  DropTime   Amount");

        for(String t: trips){
            System.out.println(id+"         "+trips);
        }

        System.out.println("----------------------------------");
    }


}
