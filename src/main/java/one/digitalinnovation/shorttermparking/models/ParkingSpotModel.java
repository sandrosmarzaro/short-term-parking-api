package one.digitalinnovation.shorttermparking.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class ParkingSpotModel {
    @Id
    private String id;
    private String license;
    private String state;
    private String brand;
    private String model;
    private String color;
    private OffsetDateTime entryDate;
    private OffsetDateTime exitDate;
    private Double bill;

    public ParkingSpotModel() {
    }

    public ParkingSpotModel(String id, String license, String state, String brand, String model, String color) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public OffsetDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(OffsetDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public OffsetDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(OffsetDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "ParkingSpotModel{" + "id=" + id + ", license=" + license + ", state=" + state + ", brand=" + brand +
                ", model=" + model + ", color=" + color + ", entryDate=" + entryDate + ", exitDate=" + exitDate +
                ", bill=" + bill + '}';
    }
}
