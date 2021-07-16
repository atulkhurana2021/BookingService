package Service;

import DAL.Repository;
import Entities.Vehicle;
import Utils.GenericOperations;

import java.util.Date;

public class UserService {
    private Repository repository;
    private GenericOperations genericOperations;

    public UserService(Repository repository) {

        this.repository = repository;
        this.genericOperations = new GenericOperations(repository);

    }

    public String bookVehicle(String userId, String vehicleType, Date startTime, Date endTime) throws Exception {
        // validate vehicletype
        if (!genericOperations.isVehicleTypeCorrect(vehicleType)) {
            throw new Exception("VehicleType does not exists");
        }
        Vehicle vehicle = genericOperations.getAvailable(userId, vehicleType, startTime, endTime);


        if (vehicle != null) {
            return ("BOOKED " + vehicle.getVehicleId());

        } else {
            return ("NOT AVAILABLE");
        }

    }
}
