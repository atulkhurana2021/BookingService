package Utils;

import DAL.Repository;
import Entities.Booking;
import Entities.Branch;
import Entities.Vehicle;
import Entities.VehicleType;
import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class GenericOperations {
    private Repository repository;

    public GenericOperations(Repository repository) {
        this.repository = repository;
    }


    public synchronized Vehicle getAvailable(String userId, String vehicleType, Date startTime, Date endTime) {
        List<String> vehicles = null;
        List<String> avail = new ArrayList<>();
        Vehicle vehicle = null;
        double price = Integer.MAX_VALUE;
        switch (vehicleType) {
            case "SEDAN":
                vehicles = repository.getAvailableSedans();
                break;
            case "SUV":
                vehicles = repository.getAvailableSUVS();
                break;
            case "HATCHBACK":
                vehicles = repository.getAvailableHatchback();
                break;
        }

        if (vehicles != null && vehicles.size() > 0) {
            List<Booking> bookings = repository.getBooking();
            for (String vehicle1 : vehicles) {
                if (isBookingPresent(bookings, vehicle1, startTime, endTime)) {
                    avail.add(vehicle1);
                }

            }


        }

        if (avail != null && avail.size() > 0) {
            for (String vehicle2 : avail) {

                if (vehicle == null) {
                    vehicle = repository.getVehicle(vehicle2);
                    price = repository.getPrice(vehicle.getBranchName(), vehicle.getVehicleType().name());
                } else {
                    Vehicle vehicle3 = repository.getVehicle(vehicle2);
                    double price1 = repository.getPrice(vehicle3.getBranchName(), vehicle3.getVehicleType().name());
                    if (price1 < price) {
                        price = price1;
                        vehicle = vehicle3;
                    }
                }

            }


        }
        if (vehicle != null) {
            repository.addBooking(new Booking(startTime, endTime, vehicle.getVehicleId(), userId));
        }

        return vehicle;
    }

    private boolean isBookingPresent(List<Booking> bookings, String vehicle, Date startTime, Date endTime) {
        for (Booking booking : bookings) {
            if (booking.getVehicleId().equalsIgnoreCase(vehicle)) {
                Date start = booking.getStartTime();
                Date end = booking.getEndTime();
                if (!(endTime.before(start) && startTime.after(end))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBranchExists(String branchName) {
        Branch branch = repository.getBranch(branchName);
        return branch != null;

    }

    public boolean isVehicleExits(String vehicleName) {
        Vehicle vehicle = repository.getVehicle(vehicleName);
        return vehicle != null;

    }

    public  boolean isVehicleTypeCorrect(String vehicleType) {
        for (VehicleType c : VehicleType.values()) {
            if (c.name().equals(vehicleType)) {
                return true;
            }
        }
        return false;
    }
}



