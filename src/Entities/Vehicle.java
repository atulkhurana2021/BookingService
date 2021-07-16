package Entities;

public abstract class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;
    private String branchName;

    public Vehicle(String vehicleId, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
