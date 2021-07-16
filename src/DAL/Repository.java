package DAL;

import Entities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Repository {


    private ConcurrentHashMap<String, Branch> branches = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Vehicle> vehicles = new ConcurrentHashMap<>();
    private List<String> availableSedans = Collections.synchronizedList(new ArrayList<String>());
    private List<String> availableSUVS = Collections.synchronizedList(new ArrayList<String>());
    private List<String> availableHatchback = Collections.synchronizedList(new ArrayList<String>());
    private List<Booking> booking = Collections.synchronizedList(new ArrayList<Booking>());
    private ConcurrentHashMap<String, Double> prices = new ConcurrentHashMap<>();


    public void addBranch(Branch branch) {
        branches.put(branch.getBranchName(), branch);
    }

    public Branch getBranch(String branch) {
        return branches.get(branch);
    }

    public Vehicle getVehicle(String vehicle) {
        return vehicles.get(vehicle);
    }

    public void addVehicleInventory(Vehicle vehicle, String branchName) {
        Branch branch = branches.get(branchName);
        branch.getVehicles().add(vehicle.getVehicleId());
        vehicle.setBranchName(branchName);
        vehicles.put(vehicle.getVehicleId(), vehicle);
        switch (vehicle.getVehicleType()) {
            case SEDAN:
                availableSedans.add(vehicle.getVehicleId());
                break;
            case SUV:
                availableSUVS.add(vehicle.getVehicleId());
                break;
            case HATCHBACK:
                availableHatchback.add(vehicle.getVehicleId());
                break;
        }
    }

    public void updatePrice(String branchName, String vehicleType, double price) {
        String key = (branchName + vehicleType).toLowerCase();
        prices.put(key, price);

    }

    public double getPrice(String branchName, String vehicleType) {
        String key = (branchName + vehicleType).toLowerCase();
        return prices.get(key);
    }


    public List<String> getAvailableSedans() {
        return availableSedans;
    }

    public List<String> getAvailableSUVS() {
        return availableSUVS;
    }

    public List<String> getAvailableHatchback() {
        return availableHatchback;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void addBooking(Booking bookingObj) {
        booking.add(bookingObj);
    }
}
