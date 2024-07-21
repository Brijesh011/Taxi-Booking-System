import java.util.*;

public class Booking {

    public static List<Taxi> createtaxis(int n){

        List<Taxi> taxis = new ArrayList<Taxi>();

        for(int i=1;i<=n;i++){

            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static List<Taxi> getFreeTaxis(List<Taxi> taxis,int pickuptime,char pickuppoint)
    {
        List<Taxi> freetaxis = new ArrayList<Taxi>();

        for(Taxi t: taxis){
                // free at pickup time cond                 // distance from curr loc to pickup loc and pickup time - freetime u need to minus 
            if(t.freetime <= pickuptime && (Math.abs((t.currentspot-'0')-(pickuppoint -'0')) <= pickuptime - t.freetime))
        {
                freetaxis.add(t);
        }
        return freetaxis;
    }
    return freetaxis;
    }

    public static void bookTaxi(int id,char pickuppoint,char droppoint,int pickuptime,List<Taxi> freetaxis){

            int min = 999;
            int dispickdrop = 0;
            int earning = 0;
            int nextfreetime = 0;
            char nextspot = 'Z';
            Taxi bookedTaxi = null; // obj creted 
            String tripdetail = "";

            for(Taxi t: freetaxis){

                int discusttaxi = Math.abs((t.currentspot-'0')-(pickuppoint -'0'))*15;

                if(discusttaxi < min){

                    bookedTaxi = t;

                    dispickdrop = Math.abs((droppoint-'0') - (pickuppoint-'0'))* 15;

                    earning = (dispickdrop-5)*10 + 100;

                    nextspot = droppoint;

                    int droptime = pickuptime + dispickdrop/15;

                    nextfreetime = droptime;

                    tripdetail = id + "           "+ id + "    "+pickuppoint +"      "+ droppoint+"    "+pickuptime +"     "+droptime+"    "+earning;
                    min = discusttaxi;
                }
            }

            bookedTaxi.setDetails(true, nextspot, nextfreetime,bookedTaxi.totalearnings +earning, tripdetail);
            System.out.println("Taxi  "+bookedTaxi.id + "booked");
    }



    public static void main(String args[]){

        List<Taxi> taxis = createtaxis(4);// if u declare it as a static u can access it without declaring any instance for the Booking clss 
        
        Scanner s = new Scanner(System.in);
        int id = 1;

        while(true){
            System.out.println("0->Book Taxis");
            System.out.println("1 -> View Details of the Taxi ");
            int choice = s.nextInt(); // object of scanner class 

            switch(choice){

                case 0 :
                {
                    int customerid = id;
                    System.out.println("Enter the pickup location");
                    char pickuppoint = s.next().charAt(0);
                    System.out.println("Enter the drop location");
                    char droppoint = s.next().charAt(0);
                    System.out.println("Enter the pickuptime");
                    int pickuptime = s.nextInt();

                    // cond 

                    if(pickuppoint < 'A' || pickuppoint > 'F' || droppoint < 'A' || droppoint > 'F'){
                        System.out.println("Enter a valid location");
                        return ;
                    }

                    List<Taxi> freetaxis = getFreeTaxis(taxis,pickuptime,pickuppoint);

                    if(freetaxis.size() == 0){
                        System.out.println("No free taxis are avaliable");
                        return;
                    }

                    Collections.sort(freetaxis,(a,b)-> a.totalearnings - b.totalearnings);

                    bookTaxi(id,pickuppoint,droppoint,pickuptime,freetaxis);
                    id++;
                    break;
                }

                case 1:
                {
                    for(Taxi t : taxis){
                        t.printDetails();
                    }
                }
                default:
                return;

            }

        }



    }
}
