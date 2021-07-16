import DAL.Repository;
import Service.AdminService;
import Service.UserService;

import java.util.Date;

public class BookingManager {

    private Repository repository;
    private AdminService adminService;
    private UserService userService;

    public BookingManager(Repository repository) {
        this.repository = repository;
        this.adminService = new AdminService(repository);
        this.userService = new UserService(repository);
    }

    public void addBranch(String branchName) throws Exception {
        adminService.addBranch(branchName);
    }

    public void allocatePrice(String branchName, String vehicleType, double price) throws Exception {
        adminService.allocatePrice(branchName, vehicleType, price);
    }

    public void addVehicle(String vehicle, String vehicleType, String branchName) throws Exception {
        adminService.addVehicle(vehicle, vehicleType, branchName);
    }

    public String bookVehicle(String userId, String vehicleType, Date startTime, Date endTime) throws Exception {
        return userService.bookVehicle(userId, vehicleType, startTime, endTime);
    }

    public void viewVehicleInventory(Date startTime, Date endTime) throws Exception {
      throw new UnsupportedOperationException();
    }
}
