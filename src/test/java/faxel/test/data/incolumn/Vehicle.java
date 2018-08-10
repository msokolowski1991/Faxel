package faxel.test.data.incolumn;

import java.util.Objects;

import faxel.annotation.Cell;

public class Vehicle {

    @Cell(index = 0)
    private String make;

    @Cell(index = 1)
    private String model;

    public Vehicle() {
    }

    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(make, vehicle.make) &&
                Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
