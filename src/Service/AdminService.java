package Service;

import DAL.Repository;
import Entities.*;
import Utils.GenericOperations;

import java.util.Date;
import java.util.List;

public class AdminService {
    private Repository repository;
    private GenericOperations genericOperations;

    public AdminService(Repository repository) {
        this.repository = repository;
        this.genericOperations = new GenericOperations(repository);
    }

    public void addBranch(String branchName) throws Exception {
        //validate if branch present already
        if (genericOperations.isBranchExists(branchName)) {
            throw new Exception("Branch already exists");
        }

        repository.addBranch(new Branch(branchName));
    }

    public void allocatePrice(String branchName, String vehicleType, double price) throws Exception {
        //validate if branch present  && vehicleType correct
        if (!genericOperations.isBranchExists(branchName)) {
            throw new Exception("Branch does not exists");
        }
        if (!genericOperations.isVehicleTypeCorrect(vehicleType)) {
            throw new Exception("VehicleType does not exists");
        }
        repository.updatePrice(branchName, vehicleType, price);

    }

    public void addVehicle(String vehicle, String vehicleType, String branchName) throws Exception {
        //validate if branch/vehile present already
        if (!genericOperations.isBranchExists(branchName)) {
            throw new Exception("Branch does not exists");
        }
        if (genericOperations.isVehicleExits(vehicle)) {
            throw new Exception("Vehicle does not exists");
        }
        Vehicle vehicleObj = null;
        switch (vehicleType.toUpperCase()) {
            case "SEDAN":
                vehicleObj = new SedanVehicle(vehicle);
                break;
            case "SUV":
                vehicleObj = new SUVVehicle(vehicle);
                break;
            case "HATCHBACK":
                vehicleObj = new HatchbackVehicle(vehicle);
                break;
            default:
                throw new Exception("Invalid Vehicle Type");

        }
        repository.addVehicleInventory(vehicleObj, branchName);
    }

    public void viewVehicleInventory(Date startTime, Date endTime) throws Exception {
        List<Booking> bookingList = repository.getBooking();
        List<Booking> eligibleList = repository.getBooking();
        for (Booking booking : bookingList) {
            if ((booking.getStartTime().after(startTime) || booking.getStartTime() == startTime) &&
                    ((booking.getEndTime().before(endTime)) | booking.getEndTime() == endTime)) {
                eligibleList.add(booking);
            }
        }
// TBD
    }
}
