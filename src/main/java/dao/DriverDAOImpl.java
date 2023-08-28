package dao;

import model.Driver;

import java.math.BigDecimal;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO {
    @Override
    public String saveDriver(String name, String surname, BigDecimal salary) {
        return null;
    }

    @Override
    public Driver getDriverById(String id) {
        return null;
    }

    @Override
    public Driver updateDriver(Driver driver) {
        return null;
    }

    @Override
    public void deleteDriver(String id) {

    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        return null;
    }

    @Override
    public List<BigDecimal> fetchAllDriversWithSalaryGreaterThan10000() {
        return null;
    }

    @Override
    public double fetchHighestSalary() {
        return 0;
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        return null;
    }

    @Override
    public long calculateNumberOfDrivers() {
        return 0;
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        return null;
    }
}
