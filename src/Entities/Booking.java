package Entities;

import java.util.Date;

public class Booking {
  private Date startTime;
  private Date endTime;
  private String vehicleId;
  private String userId;

  public Booking(Date startTime, Date endTime, String vehicleId, String userId) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.vehicleId = vehicleId;
    this.userId = userId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
