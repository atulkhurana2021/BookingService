import DAL.Repository;

import java.util.Date;


public class Driver {

    public static void main(String[] args) throws Exception {
        Repository repository = new Repository();
        BookingManager bookingManager = new BookingManager(repository);

        bookingManager.addBranch("ABC");
       // bookingManager.addBranch("ABC"); // Branch already exists


        //bookingManager.addVehicle("123","SEDAN","ABD"); //Branch does not exists
        bookingManager.addVehicle("123","SEDAN","ABC");

        bookingManager.allocatePrice("ABC","SEDAN",100.0);



        System.out.println(bookingManager.bookVehicle("ATUL","SEDAN",new Date(),new Date())); //BOOKED 123
        System.out.println(bookingManager.bookVehicle("ATUL","SEDAN",new Date(),new Date()));
    }
}
